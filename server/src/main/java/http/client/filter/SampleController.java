package http.client.filter;

import io.micronaut.buffer.netty.NettyByteBufferFactory;
import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.Flowable;

@Controller
public class SampleController {

    @Get(value = "/hello", produces = MediaType.TEXT_PLAIN)
    public String hello(@QueryValue String uid) {
        return "world " + uid;
    }

    @Get(value = "/flowable", produces = MediaType.APPLICATION_OCTET_STREAM)
    public Flowable<ByteBuffer<?>> testing(@QueryValue String uid) {
        return Flowable.range(1, 1)
                .map(ignored -> NettyByteBufferFactory.DEFAULT.wrap("Test item".getBytes()));
    }

}
