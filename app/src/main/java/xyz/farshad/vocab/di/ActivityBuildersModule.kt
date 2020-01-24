package xyz.farshad.vocab.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.farshad.vocab.activity.MainActivity

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

//    @ContributesAndroidInjector
//    abstract fun contributeLevelListActivity(): LevelListActivity
//
//    @ContributesAndroidInjector
//    abstract fun contributeWordListActivity(): WordListActivity
//
//    @ContributesAndroidInjector
//    abstract fun contributeWordPagerActivity(): WordPagerActivity
}