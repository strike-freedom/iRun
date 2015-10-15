package com.example.irun;

import java.util.List;

import com.amap.api.location.AMapLocalDayWeatherForecast;
import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.LocationManagerProxy;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

//今明后三天天气预报
public class WeatherFragment extends Fragment implements 
     AMapLocalWeatherListener {
	
	private TextView mWeatherLocationTextView;// 天气预报地点
	private TextView mTodayTimeTextView;// 今天时间
	private TextView mTomorrowTimeTextView;// 明天时间
	private TextView mNextDayTimeTextView;// 后天时间

	private TextView mTodayWeatherTextView;// 今天天气状况
	private TextView mTomorrowWeatherTextView;// 明天天气状况
	private TextView mNextDayWeatherTextView;// 后天天气状况

	private LocationManagerProxy mLocationManagerProxy;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_weather, container, false);
		
		mWeatherLocationTextView = (TextView) view.findViewById(R.id.future_weather_location_text);

		mTodayTimeTextView = (TextView) view.findViewById(R.id.today_time_text);
		mTodayWeatherTextView = (TextView) view.findViewById(R.id.today_weather_des_text);
		mTomorrowTimeTextView = (TextView) view.findViewById(R.id.tomorrow_time_text);
		mTomorrowWeatherTextView = (TextView) view.findViewById(R.id.tomorrow_weather_des_text);
		mNextDayTimeTextView = (TextView) view.findViewById(R.id.netx_day_time_text);
		mNextDayWeatherTextView = (TextView) view.findViewById(R.id.netx_day_des_text);
		
		mLocationManagerProxy = LocationManagerProxy.getInstance(getActivity());
		//获取未来天气预报
		//如果需要同时请求实时、未来三天天气，请确保定位获取位置后使用,分开调用，可忽略本句。
		mLocationManagerProxy.requestWeatherUpdates(
				LocationManagerProxy.WEATHER_TYPE_FORECAST, this);
		
		return view;
	}

	@Override
	public void onWeatherForecaseSearched(AMapLocalWeatherForecast arg0) {
		// 未来天气预报回调
		if (arg0 != null && arg0.getAMapException().getErrorCode() == 0) {

			List<AMapLocalDayWeatherForecast> forcasts = arg0
					.getWeatherForecast();
			for (int i = 0; i < forcasts.size(); i++) {
				AMapLocalDayWeatherForecast forcast = forcasts.get(i);
				switch (i) {
				case 0:
					mWeatherLocationTextView.setText(forcast.getCity());
					mTodayTimeTextView.setText("今天 ( " + forcast.getDate()
							+ " )");
					mTodayWeatherTextView.setText(forcast.getDayWeather()
							+ "    " + forcast.getDayTemp() + "℃/"
							+ forcast.getNightTemp() + "℃    "
							+ forcast.getDayWindPower()+"级");

					break;
				case 1:
					mTomorrowTimeTextView.setText("明天 ( " + forcast.getDate()
							+ " )");
					mTomorrowWeatherTextView.setText(forcast.getDayWeather()
							+ "    " + forcast.getDayTemp() + "℃/"
							+ forcast.getNightTemp() + "℃    "
							+ forcast.getDayWindPower()+"级");
					break;
				case 2:
					mNextDayTimeTextView.setText("后天 ( " + forcast.getDate()
							+ " )");
					mNextDayWeatherTextView.setText(forcast.getDayWeather()
							+ "    " + forcast.getDayTemp() + "℃/"
							+ forcast.getNightTemp() + "℃    "
							+ forcast.getDayWindPower()+"级");
					break;
				}
			}
		} else 
		{
			// 获取天气预报失败
			Toast.makeText(getActivity(),"获取天气预报失败:"
			+ arg0.getAMapException().getErrorMessage(), Toast.LENGTH_SHORT).show();
		}	
	}

	@Override
	public void onWeatherLiveSearched(AMapLocalWeatherLive arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// 销毁定位
		mLocationManagerProxy.destroy();
	}
}
