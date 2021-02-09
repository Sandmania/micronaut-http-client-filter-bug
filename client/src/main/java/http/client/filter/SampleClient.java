package http.client.filter;

import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Client("http://localhost:8088")
public interface SampleClient {

    @Get(value = "/hello", processes = MediaType.TEXT_PLAIN)
    Single<String> getHello();

    @Get(value = "/flowable", processes = MediaType.APPLICATION_OCTET_STREAM)
    Flowable<ByteBuffer<?>> getFlowable();

    @Get(value = "/flowable?uid={uid}", processes = MediaType.APPLICATION_OCTET_STREAM)
    Flowable<ByteBuffer<?>> getFlowable(@PathVariable String uid);
}
