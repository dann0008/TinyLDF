package foo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.api.server.spi.auth.EspAuthenticator;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;

@Api(name = "myApi",
     version = "v1",
     audiences = "968589437184-jgpthic1akkqc22oou6va57mvvq1nkcb.apps.googleusercontent.com",
  	 clientIds = {"968589437184-jgpthic1akkqc22oou6va57mvvq1nkcb.apps.googleusercontent.com"},
     namespace =
     @ApiNamespace(
		   ownerDomain = "helloworld.example.com",
		   ownerName = "helloworld.example.com",
		   packagePath = "")
     )

public class QuadEndpoint {

	@ApiMethod(name = "hello", httpMethod = HttpMethod.GET)
	public User Hello(User user) throws UnauthorizedException {
        if (user == null) {
			throw new UnauthorizedException("Invalid credentials");
		}
        System.out.println("Yeah:"+user.toString());
		return user;
	}

	@ApiMethod(name = "insertQuad", path = "insertQuad", httpMethod = HttpMethod.GET)
    public Entity insertQuad(@Named("sujet") String sujet, @Named("predicat") String predicat, @Named("objet") String objet, @Named("contexte") String contexte) {

        Entity quad = new Entity("Quad");
		quad.setProperty("sujet", sujet);
		quad.setProperty("predicat", predicat);
        quad.setProperty("objet", objet);
        quad.setProperty("contexte", contexte);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(quad);

		return quad;
    }

	@ApiMethod(name = "selectQuad", path = "selectQuad", httpMethod = HttpMethod.GET)
	public CollectionResponse<Entity> selectQuad(@Nullable @Named("sujet") String sujet, @Nullable @Named("predicat") String predicat, @Nullable @Named("objet") String objet, @Nullable @Named("contexte") String contexte) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Quad");
		List<Query.Filter> filters = new ArrayList<>();
	
		if (sujet != null && !sujet.isEmpty()) {
			filters.add(new Query.FilterPredicate("sujet", Query.FilterOperator.EQUAL, sujet));
		}
		if (predicat != null && !predicat.isEmpty()) {
			filters.add(new Query.FilterPredicate("predicat", Query.FilterOperator.EQUAL, predicat));
		}
		if (objet != null && !objet.isEmpty()) {
			filters.add(new Query.FilterPredicate("objet", Query.FilterOperator.EQUAL, objet));
		}
		if (contexte != null && !contexte.isEmpty()) {
			filters.add(new Query.FilterPredicate("contexte", Query.FilterOperator.EQUAL, contexte));
		}
		if (!filters.isEmpty()) {
			Query.Filter compositeFilter;
			if (filters.size() == 1) {
				compositeFilter = filters.get(0);
			} else {
				compositeFilter = Query.CompositeFilterOperator.and(filters);
			}
			query.setFilter(compositeFilter);
		}

		PreparedQuery preparedQuery = datastore.prepare(query);
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(30);
		QueryResultList<Entity> results = preparedQuery.asQueryResultList(fetchOptions);
		
		return CollectionResponse.<Entity>builder().setItems(results).build();
	}
}

