package kingson09.architecturelab;

import android.app.Application;

import kingson09.architecturelab.repository.HallInfoRepository;
import kingson09.architecturelab.repository.PeriodInfoRepository;

public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    HallInfoRepository.getRepo().loadHallInfo();
    PeriodInfoRepository.getRepo().start();
  }
}
