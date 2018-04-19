package kingson09.architecturelab.repository;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kingson09.architecturelab.bean.Period;
import kingson09.architecturelab.utils.MultiMutableLiveData;

public class PeriodInfoRepository {
  private MultiMutableLiveData<List<Period>> periods = new MultiMutableLiveData();
  private boolean running;
  private Handler mHandler = new Handler(Looper.getMainLooper());
  private final Runnable task = new Runnable() {
    @Override
    public void run() {
      if (running) {
        updateAllPeriod();
        mHandler.removeCallbacks(this);
        mHandler.postDelayed(this, 1000);
      }
    }
  };

  private PeriodInfoRepository() {
  }

  public final static PeriodInfoRepository getRepo() {
    return PeriodInfoRepository.Singleton.singleton;
  }

  private static class Singleton {
    private final static PeriodInfoRepository singleton = new PeriodInfoRepository();
  }

  public MultiMutableLiveData<List<Period>> getPeriodsInfo() {
    return periods;
  }

  public void updateAllPeriod() {
    if (periods.getValue() != null) {
      ArrayList<String> needUpdates = new ArrayList<>();
      for (Period period : periods.getValue()) {
        if (period.periodTime > 0) {
          period.periodTime--;
        } else {
          needUpdates.add(period.id);
        }
      }
      periods.postValue(periods.getValue());
      if (needUpdates.size() > 0) {
        refreshPeriodsInfo(needUpdates);
      }
    }
  }

  public void refreshAllPeriodsInfo() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        final List<Period> periodsInfo = new Gson().fromJson(JSON_PERIODS, new TypeToken<ArrayList<Period>>() {
        }.getType());
        periods.postValue(periodsInfo);
      }
    }).start();
  }

  public void refreshPeriodsInfo(ArrayList<String> needUpdates) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        final List<Period> periodsInfo = new Gson().fromJson(JSON_PERIODS, new TypeToken<ArrayList<Period>>() {
        }.getType());
        periods.postValue(periodsInfo);
      }
    }).start();
  }

  public void start() {
    refreshAllPeriodsInfo();
    if (!running) {
      running = true;
      mHandler.removeCallbacks(task);
      mHandler.post(task);
    }
  }

  public void stop() {
    if (running) {
      running = false;
      mHandler.removeCallbacks(task);
    }
  }

  public static String JSON_PERIODS =
      "[" + "{\"id\":\"0\",\"periodTime\":\"3000\"}," + "{\"id\":\"1\",\"periodTime\":\"10\"}," +
          "{\"id\":\"2\",\"periodTime\":\"100\"}," + "{\"id\":\"3\",\"periodTime\":\"3600\"}," +
          "{\"id\":\"4\",\"periodTime\":\"3887\"}," + "{\"id\":\"5\",\"periodTime\":\"3600\"}," +
          "{\"id\":\"6\",\"periodTime\":\"3500\"}," + "{\"id\":\"7\",\"periodTime\":\"36000\"}," +
          "{\"id\":\"8\",\"periodTime\":\"3600\"}," + "{\"id\":\"9\",\"periodTime\":\"3600\"}," +
          "{\"id\":\"10\",\"periodTime\":\"300\"}," + "{\"id\":\"11\",\"periodTime\":\"3600\"}," +
          "{\"id\":\"12\",\"periodTime\":\"6000\"}," + "{\"id\":\"13\",\"periodTime\":\"3600\"}," +
          "{\"id\":\"14\",\"periodTime\":\"7000\"}," + "{\"id\":\"15\",\"periodTime\":\"3600\"}," +
          "{\"id\":\"16\",\"periodTime\":\"3000\"}," + "{\"id\":\"17\",\"periodTime\":\"3600\"}," +
          "{\"id\":\"18\",\"periodTime\":\"3000\"}," + "{\"id\":\"19\",\"periodTime\":\"3600\"}" + "]";
}
