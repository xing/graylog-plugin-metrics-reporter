/**
 * This file is part of Graylog Metrics Prometheus Reporter Plugin.
 *
 * Graylog Metrics Prometheus Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Prometheus Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Prometheus Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.prometheus.providers;

import com.codahale.metrics.MetricRegistry;

import org.graylog2.database.NotFoundException;
import org.graylog2.plugin.streams.Stream;
import org.graylog2.streams.StreamRuleService;
import org.graylog2.streams.StreamService;

import io.prometheus.client.Collector;
import io.prometheus.client.Collector.MetricFamilySamples.Sample;
import io.prometheus.client.dropwizard.DropwizardExports;
import org.graylog.plugins.metrics.prometheus.DropwizardSampleBuilder;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class DropwizardExportsProvider implements Provider<DropwizardExports> {
    private final MetricRegistry metricRegistry;
    private final DropwizardSampleBuilder sampleBuilder;


    @Inject
    public DropwizardExportsProvider(MetricRegistry metricRegistry, DropwizardSampleBuilder sampleBuilder) {
        this.metricRegistry = requireNonNull(metricRegistry);
        this.sampleBuilder = sampleBuilder;
    }

    @Override
    public DropwizardExports get() {
        return new DropwizardExports(metricRegistry, sampleBuilder);
    }
}

