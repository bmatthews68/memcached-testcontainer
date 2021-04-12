package com.btmatthews.testcontainers.memcached;

import net.spy.memcached.MemcachedClientIF;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

// tag::example[]
public class JUnit4Test {

    @Rule
    public final MemcachedContainer memcachedContainer = new MemcachedContainer(DockerImageName.parse("memcached"));

    @Test
    public void addAndGet() throws IOException {
        final MemcachedClientIF client = memcachedContainer.getClient();
        client.add("key", 0, "value");
        assertThat(client.get("key")).isEqualTo("value");
    }
}
// end::example[]
