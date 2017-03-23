/*
 * Copyright 2015-2016 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.hal.client.runtime.server;

import java.util.function.Function;

import elemental.dom.Element;
import org.jboss.hal.core.runtime.server.Server;
import org.jboss.hal.core.runtime.server.ServerActions;
import org.jboss.hal.resources.Icons;

/**
 * @author Harald Pehl
 */
class ServerIcon implements Function<Server, Element> {

    private final ServerActions serverActions;

    ServerIcon(final ServerActions serverActions) {
        this.serverActions = serverActions;
    }

    @Override
    public Element apply(final Server server) {
        final Element[] element = new Element[1];
        ServerStatusSwitch sss = new ServerStatusSwitch(serverActions) {
            @Override
            protected void onPending(final Server server) {
                element[0] = Icons.unknown();
            }

            @Override
            protected void onBootErrors(final Server server) {
                element[0] = Icons.error();
            }

            @Override
            protected void onFailed(final Server server) {
                element[0] = Icons.error();
            }

            @Override
            protected void onAdminMode(final Server server) {
                element[0] = Icons.disabled();
            }

            @Override
            protected void onStarting(final Server server) {
                element[0] = Icons.disabled();
            }

            @Override
            protected void onSuspended(final Server server) {
                element[0] = Icons.pause();
            }

            @Override
            protected void onNeedsReload(final Server server) {
                element[0] = Icons.warning();
            }

            @Override
            protected void onNeedsRestart(final Server server) {
                element[0] = Icons.warning();
            }

            @Override
            protected void onRunning(final Server server) {
                element[0] = Icons.ok();
            }

            @Override
            protected void onStopped(final Server server) {
                element[0] = Icons.stopped();
            }

            @Override
            protected void onUnknown(final Server server) {
                element[0] = Icons.unknown();
            }
        };
        sss.accept(server);
        return element[0];
    }
}
