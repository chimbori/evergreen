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

package app.evergreen.ui.tools

import android.content.Context
import android.graphics.drawable.Drawable
import app.evergreen.R
import app.evergreen.extensions.drawable
import app.evergreen.extensions.withInsets
import app.evergreen.services.AppServices.opener

class LaunchPlayStore(private val context: Context) : Tool {
  override val titleText: String
    get() = context.getString(R.string.launch_play_store)

  override val mainImage: Drawable
    get() = context.drawable(R.drawable.google_play).withInsets()

  override fun doAction() {
    opener.openPlayStore(context.packageName)
  }
}
