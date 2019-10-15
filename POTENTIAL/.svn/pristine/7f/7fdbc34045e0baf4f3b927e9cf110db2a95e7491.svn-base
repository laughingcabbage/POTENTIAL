package com.potential.studyroom.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.hr.cmn.DTO;
import com.hr.cmn.WorkDiv;
import com.potential.studyroom.domain.StudyRoomVO;

public class StudyRoomDAO implements WorkDiv {
	//private String FILE_PATH = "C:\\Users\\sist\\Desktop\\StudyRoom.txt";
	private String FILE_PATH = "//211.238.142.124//hr_data//java04//StudyRoom.csv";
	private List<StudyRoomVO> list;
	final private int SEAT_COUNT = 56;
	
	public StudyRoomDAO() {
		list = this.fileToList();
	}
	
	/**파일에서 리스트로 불러오기*/
	public List<StudyRoomVO> fileToList(){
		List<StudyRoomVO> tmpList = new ArrayList<>();
		FileReader fr = null;
		BufferedReader br = null;
		File file = new File(FILE_PATH);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try{
			if( !file.isFile() ) {
				file.createNewFile();
				fw = new FileWriter(FILE_PATH,false);
				bw = new BufferedWriter(fw);
				for(int i=0 ; i<SEAT_COUNT ; i++) {
					String tmp = "null" + ","
								+(i+1) + ","
								+"null" + ","
								+"null" + ","		
								+0;
					if( i != 55 ) tmp+="\r\n";
					bw.write(tmp);
					bw.flush();
				}
			}//if
			
			fr = new FileReader(FILE_PATH);
			br = new BufferedReader(fr);
			String s = "";
			while( (s = br.readLine()) != null ) {
				String[] tmp = s.split(",");
				StudyRoomVO tmpVO = new StudyRoomVO( tmp[0], Integer.parseInt(tmp[1]), tmp[2], tmp[3], Integer.parseInt(tmp[4]) );
				tmpList.add(tmpVO);
			}
		}catch(IOException ie){
			ie.printStackTrace();
		}finally{
			try{if(br != null) br.close();}catch(IOException ie){}
			try{if(bw != null) bw.close();}catch(IOException ie){}
		}
		return tmpList;
	}
	
	/**파일로 저장*/
	public int Save(){
		return this.do_save(new DTO());
	}
	
	public List<StudyRoomVO> return_All_List() {
		return list;
	}
	
	/**정보조회
	 * @param select, key
	 * @select 0: ID, 1: 자리번호(비어있으면 0, 있으면 1)
	 * */
	public int is_Exist(int select, String key) {
		int flag = -1;
		switch(select) {
			case(0)://아이디
				for(int i=0 ; i<list.size() ; i++) {
					if( list.get(i).getID().equals(key.trim())){
						flag = i;
					}
				}
				break;
			case(1)://자리번호
				for(int i=0 ; i<list.size() ; i++) {
					if( list.get(i).getSeatNum() == Integer.parseInt(key.trim()) ){
						if(list.get(i).getID().equals("null"))
							return 0;
						else return 1;
					}
				}
				break;
		}
		return flag;
	}
	
	public String return_ID(int seat){
		for(int i=0 ; i<list.size() ; i++){
			if(list.get(i).getSeatNum() == seat){
				return list.get(i).getID();
			}
		}return "null";
	}
	
	/**현재시간*/
	public String now_Time(){
		String time = "";
		Date d = new Date();
		SimpleDateFormat t = new SimpleDateFormat("yyyy/MM/dd/HH/mm");
		time = t.format(d);
		return time;
	}
	
	
	/**입실*/
	public boolean in_Room(String ID, int seatNumber) {
		for(int i=0 ; i<list.size() ; i++)
			if( this.is_Exist(0, ID) != -1 )
				return false;
		
		list.get(seatNumber-1).setID(ID);
		list.get(seatNumber-1).setExtension(0);;
		list.get(seatNumber-1).setStartTime(this.now_Time());
		list.get(seatNumber-1).setEndTime(this.retrun_ExitTime(ID));		
//		System.out.println("seatNumber:"+seatNumber);
//		System.out.println(list.get(seatNumber-1).toString());	
		this.Save();
		return true;
	}
	
	/**입실시간 캘린더형으로 리턴*/
	public Calendar return_StartCalender(String ID) {
		int key = 0;
		if( (key = this.is_Exist(0, ID)) != -1 ) {
				String date = list.get(key).getStartTime();
				String[] tmp = date.split("/");
				Calendar c = Calendar.getInstance();
				c.set(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]));
				return c;			
		}
		return null;
	}
	
	/**연장*/
	public boolean extension(String ID) {
		boolean flag = false;
		int key = -1;
		if( (key = this.is_Exist(0, ID)) != -1 ) {
			if( list.get(key).getExtension() != 1) {
				list.get(key).setStartTime(list.get(key).getEndTime());
				Calendar c = this.return_StartCalender(ID);
				c.add(Calendar.HOUR_OF_DAY, +3);
				StringBuilder time = new StringBuilder();
				time.append(c.get(Calendar.YEAR) +"/"
								+c.get(Calendar.MONTH) +"/"
								+c.get(Calendar.DAY_OF_MONTH) +"/"
								+c.get(Calendar.HOUR_OF_DAY) +"/"
								+c.get(Calendar.MINUTE));
				list.get(key).setExtension(1);
				list.get(key).setEndTime(time.toString());
				flag = true;
				this.Save();
			}	
		}
		return flag;
	}

	/**퇴실시간*/
	public String retrun_ExitTime(String ID) {
		if( this.is_Exist(0, ID) != -1 ) {
			Calendar c = this.return_StartCalender(ID);
			c.add(Calendar.HOUR, +3);
			StringBuilder tmptime = new StringBuilder();
			tmptime.append(c.get(Calendar.YEAR) +"/"
							+c.get(Calendar.MONTH) +"/"
							+c.get(Calendar.DAY_OF_MONTH) +"/"
							+c.get(Calendar.HOUR_OF_DAY) +"/"
							+c.get(Calendar.MINUTE));
			return tmptime.toString();
		}
		return null;
	}
	
	/**퇴실*/
	public boolean exit_Room(String ID) {
		boolean flag = false;
		int key = this.is_Exist(0, ID);
		if( key != -1) {
			list.get(key).setID("null");
			list.get(key).setExtension(0);
			list.get(key).setStartTime("null");
			list.get(key).setEndTime("null");		
			flag = true;
			this.Save();
		}
		return flag;
	}
	
	/**퇴실2*/
	public boolean exit_Room(int key) {
		boolean flag = false;
		if( key != -1 && !list.get(key).getID().equals("null") ) {
			list.get(key).setID("null");
			list.get(key).setExtension(0);
			list.get(key).setStartTime("null");
			list.get(key).setEndTime("null");		
			flag = true;
		}
		return flag;
	}
	
	/**리스트인덱스 불러오기*/
	public StudyRoomVO return_Index(int index){
		StudyRoomVO outVO = list.get(index);
		return outVO;
	}
	
	
	@Override
	public int do_save(DTO dto) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		int flag = -1;
		File file = new File(FILE_PATH);
		
		try{
			if( !file.isFile() )	file.createNewFile();
			fw = new FileWriter(FILE_PATH,false);
			bw = new BufferedWriter(fw);
			for(int i=0 ; i<SEAT_COUNT ; i++) {
				String tmp = list.get(i).getID() + ","
							+list.get(i).getSeatNum() + ","
							+list.get(i).getStartTime() + ","
							+list.get(i).getEndTime() + ","		
							+list.get(i).getExtension();
				if( i != 55 ) tmp+="\r\n";
				bw.write(tmp);
				bw.flush();
			}

			flag = 1;
			
		}catch(IOException ie){
			ie.printStackTrace();
		}finally{
			try{if(bw != null) bw.close();}catch(IOException ie){}
		}
		
		
		
		return flag;
	}

	/**현재 앉아있는 자리번호 리턴*/
	public int return_NowSeat(String ID){
		int now = -1;
		int key = this.is_Exist(0, ID);
		
		if(key != -1){
			now = list.get(key).getSeatNum();
		}
		return now;
	}
	
	/**현재 사용중인 좌석번호들 리턴*/
	public List<Integer> usingList(){
		List<Integer> tmp = new ArrayList<>();
		for(int i=0 ; i<list.size() ; i++){
			if( !list.get(i).getID().equals("null") ){
				tmp.add(list.get(i).getSeatNum());
			}
		}
		return tmp;
	}
	
	/**퇴실시간캘린더 반환*/
	public Calendar return_EndCalendar(int key){
		String date = list.get(key).getEndTime();
		String[] tmp = date.split("/");
		if(tmp[0].equals("null")) return null;
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])-1, Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]));
		return c;
	}
	
	@Override
	public int do_delete(DTO dto) {
		StudyRoomVO tmpVO = (StudyRoomVO)dto;
		for(int i = 0 ; i<list.size() ; i++) {
			if( list.get(i).getID() == tmpVO.getID()) {
				list.remove(tmpVO);
				return 1;
			}
		}
		return -1;
	}

	@Override
	public int do_update(DTO dto) {
		StudyRoomVO tmpVO = (StudyRoomVO)dto;
		for(int i = 0 ; i<list.size() ; i++) {
			if( list.get(i).getID() != tmpVO.getID()) {
				list.add(tmpVO);
				return 1;
			}
		}
		return -1;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		StudyRoomVO tmpVO = (StudyRoomVO)dto;
		for(int i = 0 ; i<list.size() ; i++) {
			if( list.get(i).getID().equals(tmpVO.getID()) ) {
				return list.get(i);
			}
		}
		return null;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return null;
	}

}
