/*
 * Copyright © 2020 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.cdap.plugin.sendgrid.common.objects.stats;

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.SerializedName;
import io.cdap.cdap.api.data.schema.Schema;
import io.cdap.plugin.sendgrid.batch.source.SendGridSourceConfig;
import io.cdap.plugin.sendgrid.common.helpers.BaseObject;
import io.cdap.plugin.sendgrid.common.helpers.IBaseObject;
import io.cdap.plugin.sendgrid.common.helpers.ObjectDefinition;
import io.cdap.plugin.sendgrid.common.helpers.ObjectFieldDefinition;
import io.cdap.plugin.sendgrid.common.objects.DataSourceGroupType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AdvancedStats entity.
 */
@ObjectDefinition(
  Name = "AdvancedStats",
  Group = DataSourceGroupType.Stats,
  APIUrl = "geo/stats",
  RequiredArguments = {
      SendGridSourceConfig.PROPERTY_START_DATE
  }
)
public class AdvancedStats extends BaseObject implements IBaseObject {

  @ObjectFieldDefinition(FieldType = Schema.Type.STRING)
  @SerializedName("date")
  private String date;

  @ObjectFieldDefinition(FieldType = Schema.Type.ARRAY, NestedClass = "StatsStats")
  @SerializedName("stats")
  private List<StatsStats> stats;

  @Override
  public Map<String, Object> asMap() {
    return new ImmutableMap.Builder<String, Object>()
      .put("date", date)
      .put("stats", stats.stream().map(StatsStats::asMap).collect(Collectors.toList()))
      .build();
  }
}
