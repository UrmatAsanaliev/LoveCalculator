package com.geektech.lovecalculatore.hilt;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.room.Room;

import com.geektech.lovecalculatore.data.network.LoveApi;
import com.geektech.lovecalculatore.data.pref.Prefs;
import com.geektech.lovecalculatore.data.repo.PixabayRepository;
import com.geektech.lovecalculatore.data.room.LoveDao;
import com.geektech.lovecalculatore.data.room.LoveDataBase;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public LoveApi provideApi(){
        return new Retrofit.Builder().baseUrl("https://love-calculator.p.rapidapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(LoveApi.class);
    }

    @Provides
    public PixabayRepository provideRepository(){
        return new PixabayRepository(provideApi());
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPrefs(@ApplicationContext Context context) {
        return context.getApplicationContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    @Provides
    public Prefs providePref(SharedPreferences preferences){
        return new Prefs(preferences);
    }

    @Provides
    @Singleton
    public static LoveDataBase provideDB(Application application) {
        return Room.databaseBuilder(application, LoveDataBase.class, LoveDataBase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    public static LoveDao provideUserDataDAO(LoveDataBase appDatabase) {
        return appDatabase.caseDao();
    }
}
