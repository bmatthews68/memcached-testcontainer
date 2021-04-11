package com.btmatthews.testcontainers.memcached;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.MemcachedClientIF;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class MemcachedContainer extends GenericContainer<MemcachedContainer> {

    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("memcached");
    private static final String DEFAULT_TAG = "1.6.9";
    private static final int MEMCACHED_PORT = 11211;

    @Deprecated
    public MemcachedContainer() {
        this(DEFAULT_IMAGE_NAME.withTag(DEFAULT_TAG));
    }

    public MemcachedContainer(final String dockerImageName) {
        this(DockerImageName.parse(dockerImageName));
    }

    public MemcachedContainer(final DockerImageName dockerImageName) {
        super(dockerImageName);

        dockerImageName.assertCompatibleWith(DEFAULT_IMAGE_NAME);

        addExposedPorts(MEMCACHED_PORT);
        setWaitStrategy(new HostPortWaitStrategy());
    }

    public int getMemcachedPort() {
        return getMappedPort(MEMCACHED_PORT);
    }

    public MemcachedClientIF getClient() throws IOException {
        final InetSocketAddress address = new InetSocketAddress(getHost(), getMemcachedPort());
        return new MemcachedClient(address);
    }
}
