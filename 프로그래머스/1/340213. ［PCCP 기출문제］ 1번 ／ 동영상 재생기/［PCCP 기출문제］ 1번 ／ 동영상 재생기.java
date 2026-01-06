import java.util.*;

class Solution {
    private int convertToSeconds(String s) {  
        String[] parts = s.split(":");
        int mm = Integer.parseInt(parts[0]);
        int ss = Integer.parseInt(parts[1]);
        return mm * 60 + ss;
    }

    private String convertToString(int t) {
        int mm = t / 60;
        int ss = t % 60;
        return String.format("%02d:%02d", mm, ss);
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int opStart = convertToSeconds(op_start); 
        int opEnd = convertToSeconds(op_end);   
        int videoLen = convertToSeconds(video_len);
        int position = convertToSeconds(pos);      
        
        if(position >= opStart && position <= opEnd) position = opEnd;
        
        
        
        for(int i = 0; i < commands.length; i++){
            if(commands[i].equals("next")) {
                position += 10;
            if(position > videoLen) position = videoLen;  
            }
            else if(commands[i].equals("prev")) {
                position -= 10;                    
                if(position < 0) position = 0;     
            }
            if(position >= opStart && position <= opEnd) {
            position = opEnd;
            }
        }

        
        if(position > videoLen) position = videoLen;
        if(position < 0) position = 0;
        
        return convertToString(position);
    }
}
