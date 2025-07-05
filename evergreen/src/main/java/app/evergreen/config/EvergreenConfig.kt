// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package app.evergreen.config

import app.evergreen.config.Kind.APK
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import java.util.Date

@JsonClass(generateAdapter = true)
data class EvergreenConfig(
  val updatables: List<Updatable>
) {
  fun toCompactString() =
    updatables.joinToString(separator = "\n") { "${it.id ?: it.kind}: ${it.latestProd?.versionName}" }

  companion object {
    fun moshiAdapter(): JsonAdapter<EvergreenConfig> = Moshi.Builder()
      .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
      .build()
      .adapter(EvergreenConfig::class.java)
      .indent("  ")
      .lenient()
  }
}

@JsonClass(generateAdapter = true)
data class Updatable(
  /** Nullable because Kind.OTA and Kind.REMOTE_FIRMWARE do not need an ID. */
  var id: String? = null,
  var kind: Kind = APK,
  var latestAlpha: Version? = null,
  var latestBeta: Version? = null,
  var latestProd: Version? = null,
)

enum class Kind {
  APK,
  SYSTEM_BUILD,
  REMOTE_FIRMWARE
}

@JsonClass(generateAdapter = true)
data class Version(
  val versionName: String? = null,
  val releaseDate: Date? = null,
  val minSdkVersion: Int? = null
)
