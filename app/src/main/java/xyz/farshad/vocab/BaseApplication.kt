package xyz.farshad.vocab

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import xyz.farshad.vocab.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
