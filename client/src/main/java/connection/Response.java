// Response.java (общая для клиента и сервера)
package connection;

import seClasses.Dragon;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 22L;

    private ResponseStatus responseStatus;
    private String response;
    private Collection<Dragon> collection;
    public Response(){}

    public Response(ResponseStatus status){
        this.responseStatus = status;
    }

    public Response(ResponseStatus status, String response){
        this.response = response;
        this.responseStatus = status;
    }

    public Response(ResponseStatus status, String response, Collection<Dragon> collection){
        this.response = response;
        this.responseStatus = status;
        this.collection = collection.stream().sorted(Comparator.comparing(Dragon::getName)).toList();
    }

    public ResponseStatus getResponseStatus(){
        return responseStatus;
    }

    public String getResponse(){
        return response;
    }

    public Collection<Dragon> getCollection(){
        return collection;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Response response1 = (Response) object;
        return
                responseStatus == response1.responseStatus &&
                        Objects.equals(response, response1.response) &&
                        Objects.equals(collection, response1.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                responseStatus,
                response,
                collection
        );
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseStatus=" + responseStatus +
                ", response='" + response + '\'' +
                ", collection=" + collection +
                '}';
    }


}