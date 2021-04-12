/*
 * Copyright 2021 Brian Matthews
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.btmatthews.testcontainers.memcached;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.MemcachedClientIF;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Testcontainer module for Memcached.
 *
 * @author Brian Matthews
 */
public class MemcachedContainer extends GenericContainer<MemcachedContainer> {

    /**
     * The default container image name.
     */
    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("memcached");

    /**
     * The default version tag.
     */
    private static final String DEFAULT_TAG = "1.6.9";

    /**
     * The Memcached interface port.
     */
    private static final int MEMCACHED_PORT = 11211;

    /**
     * Create a container image with default image name and version.
     */
    @Deprecated
    public MemcachedContainer() {
        this(DEFAULT_IMAGE_NAME.withTag(DEFAULT_TAG));
    }

    /**
     * Create the container using the image name string.
     *
     * @param dockerImageName The image name string.
     */
    public MemcachedContainer(final String dockerImageName) {
        this(DockerImageName.parse(dockerImageName));
    }

    /**
     * Create the container using the parsed image name.
     *
     * @param dockerImageName The parsed image name.
     */
    public MemcachedContainer(final DockerImageName dockerImageName) {
        super(dockerImageName);

        dockerImageName.assertCompatibleWith(DEFAULT_IMAGE_NAME);

        addExposedPorts(MEMCACHED_PORT);
        setWaitStrategy(new HostPortWaitStrategy());
    }

    /**
     * Get the mapped port for Memcached.
     *
     * @return The mapped port.
     */
    public int getMemcachedPort() {
        return getMappedPort(MEMCACHED_PORT);
    }

    /**
     * Get a client that can be used to store and retrieve items in Memcached.
     *
     * @return The client.
     * @throws IOException If there was an error initialising the client.
     */
    public MemcachedClientIF getClient() throws IOException {
        final InetSocketAddress address = new InetSocketAddress(getHost(), getMemcachedPort());
        return new MemcachedClient(address);
    }
}
