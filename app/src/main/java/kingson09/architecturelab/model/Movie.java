package kingson09.architecturelab.model;

import android.databinding.ObservableField;

import kingson09.architecturelab.R;
import kingson09.architecturelab.bean.MovieInfo;
import kingson09.architecturelab.bean.Period;
import kingson09.architecturelab.view.binding.IModel;

public class Movie implements IModel {
  public String id;
  public String actors;
  public String filmName;
  public String grade;
  public String info;
  public String picaddr;
  public String shortinfo;
  public ObservableField<String> period=new ObservableField<>();

  public Movie(MovieInfo movie) {
    this.id = movie.id;
    this.actors = movie.actors;
    this.filmName = movie.filmName;
    this.grade = movie.grade;
    this.info = movie.grade;
    this.picaddr = movie.picaddr;
    this.shortinfo = movie.shortinfo;
  }

  public void setPeriod(Period period) {
    this.period.set("" + period.periodTime);
  }

  @Override
  public int layoutId() {
    return R.layout.listitem_movie_item;
  }
}
