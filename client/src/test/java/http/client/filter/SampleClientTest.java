package http.client.filter;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.Flowable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
public class SampleClientTest {

    @Inject
    SampleClient sampleClient;

    @Test
    public void testHello() {
        String response = sampleClient.getHello().blockingGet();
        assertEquals("world valueFromFilter", response);
    }

    @Test
    public void testFlowable_assumesHttpFilterSetsValue() {
        Flowable<String> response = sampleClient.getFlowable().map(bb -> new String(bb.toByteArray()));

        boolean foundItem = response
                .contains("Test item")
                .doOnError(onError -> Assertions.fail())
                .blockingGet();

        assertTrue(foundItem);

    }

    @Test
    public void testFlowable_manuallySetValue() {
        Flowable<String> response =
                sampleClient.getFlowable("manuallySetValue").map(bb -> new String(bb.toByteArray()));

        boolean foundItem = response
                .contains("Test item")
                .doOnError(onError -> Assertions.fail())
                .blockingGet();

        assertTrue(foundItem);

    }
}
