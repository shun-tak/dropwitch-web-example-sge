package com.github.shuntak;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.github.shuntak.entity.Item;
import com.github.shuntak.entity.MasterCommon;
import com.github.shuntak.entity.Post;
import com.github.shuntak.entity.User;
import com.github.shuntak.entity.dao.ItemDao;
import com.github.shuntak.entity.dao.MasterCommonDao;
import com.github.shuntak.entity.dao.PostDao;
import com.github.shuntak.entity.dao.UserDao;
import com.github.shuntak.resources.MasterResource;
import com.github.shuntak.resources.SearchItemResource;
import com.github.shuntak.resources.SearchPostResource;
import com.github.shuntak.resources.SearchUserResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

public class ExampleApplication extends Application<ExampleConfiguration> {
    @Provider
    @Consumes("application/x-msgpack")
    @Produces("application/x-msgpack")
    public static class JacksonMessagePackProvider extends JacksonJsonProvider {
        public JacksonMessagePackProvider() {
            super(new ObjectMapper(new MessagePackFactory()));
        }

        @Override
        protected boolean hasMatchingMediaType(MediaType mediaType) {
            if (mediaType != null) {
                String subtype = mediaType.getSubtype();
                return "x-msgpack".equals(subtype);
            }
            return false;
        }
    }

    private final HibernateBundle<ExampleConfiguration> hibernate = new HibernateBundle<ExampleConfiguration>(
            MasterCommon.class,
            Item.class,
            Post.class,
            User.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(ExampleConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final MigrationsBundle<ExampleConfiguration> migrations = new MigrationsBundle<ExampleConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(ExampleConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new ExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "shuntak";
    }

    @Override
    public void initialize(Bootstrap<ExampleConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(migrations);
    }

    @Override
    public void run(ExampleConfiguration configuration, Environment environment) throws Exception {
        final ItemDao itemDao = new ItemDao(hibernate.getSessionFactory());
        final MasterCommonDao masterCommonDao = new MasterCommonDao(hibernate.getSessionFactory());
        final PostDao postDao = new PostDao(hibernate.getSessionFactory());
        final UserDao userDao = new UserDao(hibernate.getSessionFactory());

        environment.jersey().register(JacksonMessagePackProvider.class);
        environment.jersey().register(new MasterResource(masterCommonDao));
        environment.jersey().register(new SearchItemResource(itemDao, postDao));
        environment.jersey().register(new SearchPostResource(itemDao, postDao, userDao));
        environment.jersey().register(new SearchUserResource(postDao, userDao));
    }
}
