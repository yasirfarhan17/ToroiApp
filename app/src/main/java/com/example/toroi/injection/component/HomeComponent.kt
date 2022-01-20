package com.example.toroi.injection.component

import com.example.toroi.injection.scope.ActivityScope
import com.example.toroi.ui.details_view.DetailsActivity
import com.example.toroi.ui.home.HomeActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface HomeComponent {
  @Subcomponent.Factory
  interface Factory {
    fun create(): HomeComponent
  }

  fun inject(activity: HomeActivity)
  fun inject(activity: DetailsActivity)

}