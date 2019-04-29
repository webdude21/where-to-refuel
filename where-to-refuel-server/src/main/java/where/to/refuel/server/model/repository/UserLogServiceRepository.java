package where.to.refuel.server.model.repository;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.Success;
import io.reactivex.Flowable;
import org.bson.Document;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;

@Singleton
public class UserLogServiceRepository {

  private static final String USER_INFO_COLLECTION = "user_info_log";
  private static final String DATABASE_NAME = "heroku_5kk33l34";
  private final MongoClient mongoClient;

  @Inject
  public UserLogServiceRepository(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public Flowable<Success> logUserInfo(Coordinates location, FuelType fuelType) {
    var collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(USER_INFO_COLLECTION);
    Document document = new Document();
    document.put("requestedOn", LocalDateTime.now());
    document.put("location", location);
    document.put("fuelType", fuelType.getKey());
    return Flowable.fromPublisher(collection.insertOne(document));
  }
}
