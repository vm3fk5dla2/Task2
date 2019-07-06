package com.example.task1.ui.main.Tab3;

import com.naver.maps.geometry.LatLng;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class LocationData {


  public ArrayList<LatLng> stationLocList = new ArrayList<LatLng>();
  public ArrayList<String> stationNameList = new ArrayList<String>();
  public ArrayList<LatLng> pathLocList = new ArrayList<LatLng>();
  public ArrayList<ArrayList<String>> timeTable = new ArrayList<ArrayList<String>>();


  public void setData() {
    stationLocList.add(new LatLng(36.3733734, 127.3592182));   //학사식당
    stationLocList.add(new LatLng(36.372921, 127.3616884));    //Sport Complex
    stationLocList.add(new LatLng(36.3707786, 127.3625589));   //창의학습관
    stationLocList.add(new LatLng(36.3703879, 127.3658091));   //의과학센터
    stationLocList.add(new LatLng(36.3693934, 127.3693892));   //Medical Center
    stationLocList.add(new LatLng(36.368708, 127.3671386));   //나노종합기술원
    stationLocList.add(new LatLng(36.3664233, 127.3637844));   //정문
    stationLocList.add(new LatLng(36.3681931, 127.3622346));   //오리연못
    stationLocList.add(new LatLng(36.370393, 127.3604109));   //교육지원동 건너편
    stationLocList.add(new LatLng(36.3715681, 127.355487));   //나눔관
    stationLocList.add(new LatLng(36.368301, 127.3562427));  //희망-다솜관

    stationNameList.add("학사식당");
    stationNameList.add("Sport Complex");
    stationNameList.add("창의학습관");
    stationNameList.add("의과학센터");
    stationNameList.add("Medical Center");
    stationNameList.add("나노종합기술원");
    stationNameList.add("정문");
    stationNameList.add("오리연못");
    stationNameList.add("교육지원동 건너편");
    stationNameList.add("나눔관");
    stationNameList.add("희망-다솜관");

    pathLocList.add(new LatLng(36.3734836, 127.3590564));
    pathLocList.add(new LatLng(36.3734882, 127.3610436));
    pathLocList.add(new LatLng(36.3726352, 127.3621069));
    pathLocList.add(new LatLng(36.3721958, 127.3621105));
    pathLocList.add(new LatLng(36.3717441, 127.3613504));
    pathLocList.add(new LatLng(36.3716629, 127.3613311));
    pathLocList.add(new LatLng(36.3715683, 127.3613452));
    pathLocList.add(new LatLng(36.3711566, 127.3619758));
    pathLocList.add(new LatLng(36.3708957, 127.3624333));
    pathLocList.add(new LatLng(36.3708035, 127.3626898));
    pathLocList.add(new LatLng(36.3707184, 127.3631275));
    pathLocList.add(new LatLng(36.3707075, 127.3634759));
    pathLocList.add(new LatLng(36.3707417, 127.363827));
    pathLocList.add(new LatLng(36.3708413, 127.3641864));
    pathLocList.add(new LatLng(36.3711216, 127.3649328));
    pathLocList.add(new LatLng(36.3711354, 127.365139));
    pathLocList.add(new LatLng(36.3710702, 127.3653147));
    pathLocList.add(new LatLng(36.3709757, 127.3654096));
    pathLocList.add(new LatLng(36.3699579, 127.3662196));
    pathLocList.add(new LatLng(36.3699286, 127.3662893));
    pathLocList.add(new LatLng(36.3699445, 127.3663784));
    pathLocList.add(new LatLng(36.3707418, 127.3678737));
    pathLocList.add(new LatLng(36.3707531, 127.3679322));
    pathLocList.add(new LatLng(36.3707329, 127.3680102));
    pathLocList.add(new LatLng(36.3701322, 127.3689504));
    pathLocList.add(new LatLng(36.3700331, 127.3690593));
    pathLocList.add(new LatLng(36.3699701, 127.3691179));
    pathLocList.add(new LatLng(36.3694881, 127.3694699));
    pathLocList.add(new LatLng(36.3694453, 127.3694421));
    pathLocList.add(new LatLng(36.3694137, 127.3694366));
    pathLocList.add(new LatLng(36.3693777, 127.369445));
    pathLocList.add(new LatLng(36.3693507, 127.3694729));
    pathLocList.add(new LatLng(36.3691725, 127.3694007));
    pathLocList.add(new LatLng(36.3690011, 127.3692756));
    pathLocList.add(new LatLng(36.3688883, 127.3691616));
    pathLocList.add(new LatLng(36.3687912, 127.3690029));
    pathLocList.add(new LatLng(36.3687143, 127.3687828));
    pathLocList.add(new LatLng(36.3686983, 127.3685571));
    pathLocList.add(new LatLng(36.3687318, 127.3683174));
    pathLocList.add(new LatLng(36.3688195, 127.3680664));
    pathLocList.add(new LatLng(36.3691347, 127.3678067));
    pathLocList.add(new LatLng(36.3691504, 127.3677593));
    pathLocList.add(new LatLng(36.3691436, 127.3677064));
    pathLocList.add(new LatLng(36.3688951, 127.3672163));
    pathLocList.add(new LatLng(36.368841, 127.3671662));
    pathLocList.add(new LatLng(36.3687869, 127.3671524));
    pathLocList.add(new LatLng(36.3686113, 127.3672948));
    pathLocList.add(new LatLng(36.3684648, 127.3673592));
    pathLocList.add(new LatLng(36.3682891, 127.3673901));
    pathLocList.add(new LatLng(36.3680546, 127.3673292));
    pathLocList.add(new LatLng(36.3679847, 127.3672903));
    pathLocList.add(new LatLng(36.3678132, 127.3671317));
    pathLocList.add(new LatLng(36.3666569, 127.3650408));
    pathLocList.add(new LatLng(36.3665417, 127.3648181));
    pathLocList.add(new LatLng(36.3664761, 127.3645952));
    pathLocList.add(new LatLng(36.3663239, 127.363531));
    pathLocList.add(new LatLng(36.3690668, 127.3615726));
    pathLocList.add(new LatLng(36.369265, 127.3614329));
    pathLocList.add(new LatLng(36.3696433, 127.3611284));
    pathLocList.add(new LatLng(36.3698977, 127.3608799));
    pathLocList.add(new LatLng(36.3702398, 127.3605087));
    pathLocList.add(new LatLng(36.3704784, 127.3602017));
    pathLocList.add(new LatLng(36.3706966, 127.3598919));
    pathLocList.add(new LatLng(36.3709733, 127.3594316));
    pathLocList.add(new LatLng(36.3711712, 127.3590494));
    pathLocList.add(new LatLng(36.3714072, 127.3585028));
    pathLocList.add(new LatLng(36.3715375, 127.3581263));
    pathLocList.add(new LatLng(36.371744, 127.3574124));
    pathLocList.add(new LatLng(36.3718429, 127.3571809));
    pathLocList.add(new LatLng(36.3718427, 127.3570416));
    pathLocList.add(new LatLng(36.3717908, 127.3569609));
    pathLocList.add(new LatLng(36.3716283, 127.3567689));
    pathLocList.add(new LatLng(36.3715604, 127.3565878));
    pathLocList.add(new LatLng(36.3715377, 127.3564652));
    pathLocList.add(new LatLng(36.3715296, 127.3553533));
    pathLocList.add(new LatLng(36.3714866, 127.3551973));
    pathLocList.add(new LatLng(36.3713514, 127.3551641));
    pathLocList.add(new LatLng(36.369963, 127.3551722));
    pathLocList.add(new LatLng(36.3697174, 127.35522));
    pathLocList.add(new LatLng(36.3695552, 127.3552984));
    pathLocList.add(new LatLng(36.3682467, 127.3561313));
    pathLocList.add(new LatLng(36.3681995, 127.3561843));
    pathLocList.add(new LatLng(36.3682063, 127.356215));
    pathLocList.add(new LatLng(36.3682558, 127.3562009));
    pathLocList.add(new LatLng(36.3696454, 127.3553121));
    pathLocList.add(new LatLng(36.3698189, 127.3552533));
    pathLocList.add(new LatLng(36.3700239, 127.3552278));
    pathLocList.add(new LatLng(36.3712996, 127.3552227));
    pathLocList.add(new LatLng(36.3714236, 127.3552364));
    pathLocList.add(new LatLng(36.371595, 127.3553225));
    pathLocList.add(new LatLng(36.371692, 127.3553948));
    pathLocList.add(new LatLng(36.37185, 127.3556174));
    pathLocList.add(new LatLng(36.3722251, 127.356425));
    pathLocList.add(new LatLng(36.3722838, 127.3565057));
    pathLocList.add(new LatLng(36.372392, 127.356511));
    pathLocList.add(new LatLng(36.3725722, 127.3563992));
    pathLocList.add(new LatLng(36.3727614, 127.3563125));
    pathLocList.add(new LatLng(36.3729732, 127.3562564));
    pathLocList.add(new LatLng(36.373034, 127.3562535));
    pathLocList.add(new LatLng(36.3734285, 127.3562444));
    pathLocList.add(new LatLng(36.3734646, 127.3562945));
    pathLocList.add(new LatLng(36.3734836, 127.3590564));

    ArrayList<String> semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:45");
    semiTimeTable.add("08:55");
    semiTimeTable.add("09:15");
    semiTimeTable.add("09:25");
    semiTimeTable.add("09:45");
    semiTimeTable.add("09:55");
    semiTimeTable.add("10:15");
    semiTimeTable.add("10:25");
    semiTimeTable.add("10:45");
    semiTimeTable.add("10:55");
    semiTimeTable.add("11:15");
    semiTimeTable.add("11:25");
    semiTimeTable.add("11:45");
    semiTimeTable.add("12:55");
    semiTimeTable.add("13:15");
    semiTimeTable.add("13:25");
    semiTimeTable.add("13:45");
    semiTimeTable.add("13:55");
    semiTimeTable.add("14:15");
    semiTimeTable.add("14:25");
    semiTimeTable.add("14:45");
    semiTimeTable.add("14:55");
    semiTimeTable.add("15:15");
    semiTimeTable.add("15:25");
    semiTimeTable.add("15:45");
    semiTimeTable.add("15:55");
    semiTimeTable.add("16:15");
    semiTimeTable.add("16:25");
    semiTimeTable.add("16:45");
    semiTimeTable.add("16:55");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:46");
    semiTimeTable.add("08:56");
    semiTimeTable.add("09:16");
    semiTimeTable.add("09:26");
    semiTimeTable.add("09:46");
    semiTimeTable.add("09:56");
    semiTimeTable.add("10:16");
    semiTimeTable.add("10:26");
    semiTimeTable.add("10:46");
    semiTimeTable.add("10:56");
    semiTimeTable.add("11:16");
    semiTimeTable.add("11:26");
    semiTimeTable.add("11:46");
    semiTimeTable.add("12:56");
    semiTimeTable.add("13:16");
    semiTimeTable.add("13:26");
    semiTimeTable.add("13:46");
    semiTimeTable.add("13:56");
    semiTimeTable.add("14:16");
    semiTimeTable.add("14:26");
    semiTimeTable.add("14:46");
    semiTimeTable.add("14:56");
    semiTimeTable.add("15:16");
    semiTimeTable.add("15:26");
    semiTimeTable.add("15:46");
    semiTimeTable.add("15:56");
    semiTimeTable.add("16:16");
    semiTimeTable.add("16:26");
    semiTimeTable.add("16:46");
    semiTimeTable.add("16:56");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:48");
    semiTimeTable.add("08:58");
    semiTimeTable.add("09:18");
    semiTimeTable.add("09:28");
    semiTimeTable.add("09:48");
    semiTimeTable.add("09:58");
    semiTimeTable.add("10:18");
    semiTimeTable.add("10:28");
    semiTimeTable.add("10:48");
    semiTimeTable.add("10:58");
    semiTimeTable.add("11:18");
    semiTimeTable.add("11:28");
    semiTimeTable.add("11:48");
    semiTimeTable.add("12:58");
    semiTimeTable.add("13:18");
    semiTimeTable.add("13:28");
    semiTimeTable.add("13:48");
    semiTimeTable.add("13:58");
    semiTimeTable.add("14:18");
    semiTimeTable.add("14:28");
    semiTimeTable.add("14:48");
    semiTimeTable.add("14:58");
    semiTimeTable.add("15:18");
    semiTimeTable.add("15:28");
    semiTimeTable.add("15:48");
    semiTimeTable.add("15:58");
    semiTimeTable.add("16:18");
    semiTimeTable.add("16:28");
    semiTimeTable.add("16:48");
    semiTimeTable.add("16:58");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:49");
    semiTimeTable.add("08:59");
    semiTimeTable.add("09:19");
    semiTimeTable.add("09:29");
    semiTimeTable.add("09:49");
    semiTimeTable.add("09:59");
    semiTimeTable.add("10:19");
    semiTimeTable.add("10:29");
    semiTimeTable.add("10:49");
    semiTimeTable.add("10:59");
    semiTimeTable.add("11:19");
    semiTimeTable.add("11:29");
    semiTimeTable.add("11:49");
    semiTimeTable.add("12:59");
    semiTimeTable.add("13:19");
    semiTimeTable.add("13:29");
    semiTimeTable.add("13:49");
    semiTimeTable.add("13:59");
    semiTimeTable.add("14:19");
    semiTimeTable.add("14:29");
    semiTimeTable.add("14:49");
    semiTimeTable.add("14:59");
    semiTimeTable.add("15:19");
    semiTimeTable.add("15:29");
    semiTimeTable.add("15:49");
    semiTimeTable.add("15:59");
    semiTimeTable.add("16:19");
    semiTimeTable.add("16:29");
    semiTimeTable.add("16:49");
    semiTimeTable.add("16:59");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:51");
    semiTimeTable.add("09:01");
    semiTimeTable.add("09:21");
    semiTimeTable.add("09:31");
    semiTimeTable.add("09:51");
    semiTimeTable.add("10:01");
    semiTimeTable.add("10:21");
    semiTimeTable.add("10:31");
    semiTimeTable.add("10:51");
    semiTimeTable.add("11:01");
    semiTimeTable.add("11:21");
    semiTimeTable.add("11:31");
    semiTimeTable.add("12:51");
    semiTimeTable.add("13:01");
    semiTimeTable.add("13:21");
    semiTimeTable.add("13:31");
    semiTimeTable.add("13:51");
    semiTimeTable.add("14:01");
    semiTimeTable.add("14:21");
    semiTimeTable.add("14:31");
    semiTimeTable.add("14:51");
    semiTimeTable.add("15:01");
    semiTimeTable.add("15:21");
    semiTimeTable.add("15:31");
    semiTimeTable.add("15:51");
    semiTimeTable.add("16:01");
    semiTimeTable.add("16:21");
    semiTimeTable.add("16:31");
    semiTimeTable.add("16:51");
    semiTimeTable.add("17:01");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:52");
    semiTimeTable.add("09:02");
    semiTimeTable.add("09:22");
    semiTimeTable.add("09:32");
    semiTimeTable.add("09:52");
    semiTimeTable.add("10:02");
    semiTimeTable.add("10:22");
    semiTimeTable.add("10:32");
    semiTimeTable.add("10:52");
    semiTimeTable.add("11:02");
    semiTimeTable.add("11:22");
    semiTimeTable.add("11:32");
    semiTimeTable.add("12:52");
    semiTimeTable.add("13:02");
    semiTimeTable.add("13:22");
    semiTimeTable.add("13:32");
    semiTimeTable.add("13:52");
    semiTimeTable.add("14:02");
    semiTimeTable.add("14:22");
    semiTimeTable.add("14:32");
    semiTimeTable.add("14:52");
    semiTimeTable.add("15:02");
    semiTimeTable.add("15:22");
    semiTimeTable.add("15:32");
    semiTimeTable.add("15:52");
    semiTimeTable.add("16:02");
    semiTimeTable.add("16:22");
    semiTimeTable.add("16:32");
    semiTimeTable.add("16:52");
    semiTimeTable.add("17:02");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:53");
    semiTimeTable.add("09:03");
    semiTimeTable.add("09:23");
    semiTimeTable.add("09:33");
    semiTimeTable.add("09:53");
    semiTimeTable.add("10:03");
    semiTimeTable.add("10:23");
    semiTimeTable.add("10:33");
    semiTimeTable.add("10:53");
    semiTimeTable.add("11:03");
    semiTimeTable.add("11:23");
    semiTimeTable.add("11:33");
    semiTimeTable.add("12:53");
    semiTimeTable.add("13:03");
    semiTimeTable.add("13:23");
    semiTimeTable.add("13:33");
    semiTimeTable.add("13:53");
    semiTimeTable.add("14:03");
    semiTimeTable.add("14:23");
    semiTimeTable.add("14:33");
    semiTimeTable.add("14:53");
    semiTimeTable.add("15:03");
    semiTimeTable.add("15:23");
    semiTimeTable.add("15:33");
    semiTimeTable.add("15:53");
    semiTimeTable.add("16:03");
    semiTimeTable.add("16:23");
    semiTimeTable.add("16:33");
    semiTimeTable.add("16:53");
    semiTimeTable.add("17:03");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:54");
    semiTimeTable.add("09:04");
    semiTimeTable.add("09:24");
    semiTimeTable.add("09:34");
    semiTimeTable.add("09:54");
    semiTimeTable.add("10:04");
    semiTimeTable.add("10:24");
    semiTimeTable.add("10:34");
    semiTimeTable.add("10:54");
    semiTimeTable.add("11:04");
    semiTimeTable.add("11:24");
    semiTimeTable.add("11:34");
    semiTimeTable.add("12:54");
    semiTimeTable.add("13:04");
    semiTimeTable.add("13:24");
    semiTimeTable.add("13:34");
    semiTimeTable.add("13:54");
    semiTimeTable.add("14:04");
    semiTimeTable.add("14:24");
    semiTimeTable.add("14:34");
    semiTimeTable.add("14:54");
    semiTimeTable.add("15:04");
    semiTimeTable.add("15:24");
    semiTimeTable.add("15:34");
    semiTimeTable.add("15:54");
    semiTimeTable.add("16:04");
    semiTimeTable.add("16:24");
    semiTimeTable.add("16:34");
    semiTimeTable.add("16:54");
    semiTimeTable.add("17:04");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:55");
    semiTimeTable.add("09:05");
    semiTimeTable.add("09:25");
    semiTimeTable.add("09:35");
    semiTimeTable.add("09:55");
    semiTimeTable.add("10:05");
    semiTimeTable.add("10:25");
    semiTimeTable.add("10:35");
    semiTimeTable.add("10:55");
    semiTimeTable.add("11:05");
    semiTimeTable.add("11:25");
    semiTimeTable.add("11:35");
    semiTimeTable.add("12:55");
    semiTimeTable.add("13:05");
    semiTimeTable.add("13:25");
    semiTimeTable.add("13:35");
    semiTimeTable.add("13:55");
    semiTimeTable.add("14:05");
    semiTimeTable.add("14:25");
    semiTimeTable.add("14:35");
    semiTimeTable.add("14:55");
    semiTimeTable.add("15:05");
    semiTimeTable.add("15:25");
    semiTimeTable.add("15:35");
    semiTimeTable.add("15:55");
    semiTimeTable.add("16:05");
    semiTimeTable.add("16:25");
    semiTimeTable.add("16:35");
    semiTimeTable.add("16:55");
    semiTimeTable.add("17:05");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:56");
    semiTimeTable.add("09:06");
    semiTimeTable.add("09:26");
    semiTimeTable.add("09:36");
    semiTimeTable.add("09:56");
    semiTimeTable.add("10:06");
    semiTimeTable.add("10:26");
    semiTimeTable.add("10:36");
    semiTimeTable.add("10:56");
    semiTimeTable.add("11:06");
    semiTimeTable.add("11:26");
    semiTimeTable.add("11:36");
    semiTimeTable.add("12:56");
    semiTimeTable.add("13:06");
    semiTimeTable.add("13:26");
    semiTimeTable.add("13:36");
    semiTimeTable.add("13:56");
    semiTimeTable.add("14:06");
    semiTimeTable.add("14:26");
    semiTimeTable.add("14:36");
    semiTimeTable.add("14:56");
    semiTimeTable.add("15:06");
    semiTimeTable.add("15:26");
    semiTimeTable.add("15:36");
    semiTimeTable.add("15:56");
    semiTimeTable.add("16:06");
    semiTimeTable.add("16:26");
    semiTimeTable.add("16:36");
    semiTimeTable.add("16:56");
    semiTimeTable.add("17:06");
    timeTable.add(semiTimeTable);

    semiTimeTable = new ArrayList<String>();
    semiTimeTable.add("08:57");
    semiTimeTable.add("09:07");
    semiTimeTable.add("09:27");
    semiTimeTable.add("09:37");
    semiTimeTable.add("09:57");
    semiTimeTable.add("10:07");
    semiTimeTable.add("10:27");
    semiTimeTable.add("10:37");
    semiTimeTable.add("10:57");
    semiTimeTable.add("11:07");
    semiTimeTable.add("11:27");
    semiTimeTable.add("11:37");
    semiTimeTable.add("12:57");
    semiTimeTable.add("13:07");
    semiTimeTable.add("13:27");
    semiTimeTable.add("13:37");
    semiTimeTable.add("13:57");
    semiTimeTable.add("14:07");
    semiTimeTable.add("14:27");
    semiTimeTable.add("14:37");
    semiTimeTable.add("14:57");
    semiTimeTable.add("15:07");
    semiTimeTable.add("15:27");
    semiTimeTable.add("15:37");
    semiTimeTable.add("15:57");
    semiTimeTable.add("16:07");
    semiTimeTable.add("16:27");
    semiTimeTable.add("16:37");
    semiTimeTable.add("16:57");
    semiTimeTable.add("17:07");
    timeTable.add(semiTimeTable);


  }

  public int getStationLocSize() {
    return stationLocList.size();
  }

  public String[] getLatestTime(int stationNum) {
    String[] latestTime = new String[2];
    LocalTime curTime = LocalTime.now();
    LocalDate curDate = LocalDate.now();
    DayOfWeek curDay = curDate.getDayOfWeek();

    if (curDay == DayOfWeek.SATURDAY || curDay == DayOfWeek.SUNDAY) {
      latestTime[0] = "No Bus";
      latestTime[1] = "No Bus";
    }

    for (int i = 0; i < timeTable.get(stationNum).size(); i++) {
      LocalTime time = LocalTime.parse(timeTable.get(stationNum).get(i));

      if (curTime.isBefore(time)) {
        latestTime[0] = timeTable.get(stationNum).get(i);

        if (i == timeTable.get(stationNum).size() - 1) {
          latestTime[1] = "No Bus";
        } else {
          latestTime[1] = timeTable.get(stationNum).get(i + 1);
        }

        break;
      }

    }

    if (latestTime[0] == null && latestTime[1] == null) {
      latestTime[0] = "No Bus";
      latestTime[1] = "No Bus";
    }

    return latestTime;
  }
}


