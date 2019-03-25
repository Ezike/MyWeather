package ezike.tobenna.myweather.di.module;

import dagger.Binds;
import dagger.Module;
import ezike.tobenna.myweather.provider.UnitProvider;
import ezike.tobenna.myweather.provider.UnitProviderImpl;

/**
 * @author tobennaezike
 * @since 23/03/19
 */
@Module
public abstract class UnitModule {

    @Binds
    abstract UnitProvider provideUnitProvider(UnitProviderImpl unitProvider);
}
