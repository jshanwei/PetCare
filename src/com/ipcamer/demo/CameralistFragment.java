package com.ipcamer.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vstc2.nativecaller.NativeCaller;

import com.ipcamer.demo.R;
import com.ipcamer.demo.AddCameraActivity.StartPPPPThread;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.ipcamer.demo.BridgeService.AddCameraInterface;
import com.ipcamer.demo.BridgeService.IpcamClientInterface;
import com.ipcamer.demo.PlayActivity.feed;



public class CameralistFragment extends Fragment {
	private String uid;
	private ListView listview;
	private SimpleAdapter adapter;
	private ImageButton addCamera;
	//public List<Map<String,Object>> cameraList=new ArrayList<Map<String,Object>>();
	//private AddCameraActivity newAddCameraActivity;
	
	private ConnectCameraListener connectcamera;
	public interface ConnectCameraListener{
		public void ConnectCamera(String id,int num);
		public void linksecond(String id);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
			Bundle savedInstanceState){
		View cameralistLayout=inflater.inflate(R.layout.main_camera_gridview,container,false);
		uid=((MainActivity)getActivity()).getUid();
		System.out.println("uid is "+uid);
		createAdapterData();
		listview=(ListView)cameralistLayout.findViewById(R.id.listviewCamera);
		addCamera=(ImageButton)cameralistLayout.findViewById(R.id.imgAddDevice);
		adapter=new SimpleAdapter(getActivity(), SystemValue.cameraList, R.layout.mian_camera_listview,
				new String[]{"uid","img","cameraName","status"},
				new int[]{R.id.camera_info_uid,R.id.imagecamer,R.id.camera_info_name,
			R.id.camera_info_status});				
		listview.setAdapter(adapter);
		
		
		
		listview.setOnItemClickListener(new OnItemClickListener(){			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ListView listView=(ListView)parent;
				HashMap<String,String> map=(HashMap<String,String>) listView.getItemAtPosition(position);
				String id1=new String();
				id1=map.get("uid");
				Log.d("CameralistFragment 连接",id1);
				String status=new String();
				status=map.get("status");
				if(status.equals("在线")){
					
					Intent intent = new Intent(getActivity(),
							PlayActivity.class);
					Bundle mBundle=new Bundle();
					mBundle.putSerializable("cameraInfo", id1);
					intent.putExtras(mBundle);					
					getActivity().startActivity(intent);
					
				}else{
				/*Intent i=new Intent();
				i.setClass(getActivity(), AddCameraActivity.class);
				Bundle mBundle=new Bundle();
				mBundle.putSerializable("cameraInfo", id1);
				i.putExtras(mBundle);
				getActivity().startActivity(i);	*/
				//执行点击连接功能，和点击播放功能。
				connectcamera.ConnectCamera(id1,position);	
				}
				
				
				
			}
			
		});
		addCamera.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		return cameralistLayout;
				
	}
	
	/*private List<Map<String,Object>> getData(){
		final List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", "VSTC439989PTRKJ");
		map.put("cameraName","实验室喂食机1");
		//map.put("img",R.drawable.cameralist_settting_button);
		map.put("status", "点击进入");
		list.add(map);	
		Map<String,Object> map4=new HashMap<String,Object>();
		map4.put("uid", "VSTC440015EPMHF");
		map4.put("cameraName","实验室喂食机1独立摄像机");
		//map.put("img",R.drawable.cameralist_settting_button);
		map4.put("status", "点击进入");
		list.add(map4);	
		Map<String,Object> map5=new HashMap<String,Object>();
		map5.put("uid", "VSTC306312TVNJJ");
		map5.put("cameraName","实验室喂食机2");
		//map.put("img",R.drawable.cameralist_settting_button);
		map5.put("status", "点击进入");
		list.add(map5);
		
		return list;		
	}*/
	
	public void createAdapterData(){
		SystemValue.cameraList.clear();		
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid","VSTC542187NXFGW");
		map.put("cameraName","实验室喂食机1");
		map.put("status", "点击连接");
		Map<String,Object> map2=new HashMap<String,Object>();
		SystemValue.cameraList.add(map);
		map2.put("uid","VSTC440015EPMHF");
		map2.put("cameraName","实验室独立IPCam");
		map2.put("status", "点击连接");
		SystemValue.cameraList.add(map2);
		Map<String,Object> map3=new HashMap<String,Object>();
		map3.put("uid","VSTC306312TVNJJ");
		map3.put("cameraName","实验室喂食机2");
		map3.put("status", "点击连接");
		SystemValue.cameraList.add(map3);
		
		Map<String,Object> map9=new HashMap<String,Object>();
		map9.put("uid","VSTC538656TEBBM");
		map9.put("cameraName","许师傅办公室01");
		map9.put("status", "点击连接");
		SystemValue.cameraList.add(map9);
		Map<String,Object> map10=new HashMap<String,Object>();
		map10.put("uid","VSTC509938VKBTR");
		map10.put("cameraName","许师傅办公室02");
		map10.put("status", "点击连接");
		SystemValue.cameraList.add(map10);
		
		Map<String,Object> map4=new HashMap<String,Object>();
		map4.put("uid","VSTC306907PSSPY");
		map4.put("cameraName","喂食机测试01_user");
		map4.put("status", "点击连接");
		SystemValue.cameraList.add(map4);
		Map<String,Object> map13=new HashMap<String,Object>();
		map13.put("uid","VSTC510422EBVHF");
		map13.put("cameraName","喂食机测试02_user");
		map13.put("status", "点击连接");
		SystemValue.cameraList.add(map13);
		
		Map<String,Object> map6=new HashMap<String,Object>();
		map6.put("uid","VSTC234402PGLDV");
		map6.put("cameraName","喂食机测试04");
		map6.put("status", "点击连接");
		SystemValue.cameraList.add(map6);
		Map<String,Object> map11=new HashMap<String,Object>();
		map11.put("uid","VSTC538662YGVHJ");
		map11.put("cameraName","喂食机测试07");
		map11.put("status", "点击连接");
		SystemValue.cameraList.add(map11);
		Map<String,Object> map12=new HashMap<String,Object>();
		map12.put("uid","VSTC575774PPXUN");
		map12.put("cameraName","喂食机测试08");
		map12.put("status", "点击连接");
		SystemValue.cameraList.add(map12);
		
		
		
		
		
	}
	
	public void refresh(){
		adapter.notifyDataSetChanged();
		Log.d("CameralistFragment refresh()", "excute refresh()");
	}
	
	private void changeStatus(String s){
		
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try{
			connectcamera=(ConnectCameraListener)activity;
			
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString()+"must implement ConnectCammeraListener");
		}
	}
	

	
	
	
	
}
