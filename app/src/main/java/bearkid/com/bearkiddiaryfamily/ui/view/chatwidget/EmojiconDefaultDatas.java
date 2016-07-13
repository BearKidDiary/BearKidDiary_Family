package bearkid.com.bearkiddiaryfamily.ui.view.chatwidget;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.view.chatwidget.Emojicon.Type;

public class EmojiconDefaultDatas {
    
    private static String[] emojis = new String[]{
        EmojiconUtils.ee_1,
        EmojiconUtils.ee_2,
        EmojiconUtils.ee_3,
        EmojiconUtils.ee_4,
        EmojiconUtils.ee_5,
        EmojiconUtils.ee_6,
        EmojiconUtils.ee_7,
        EmojiconUtils.ee_8,
        EmojiconUtils.ee_9,
        EmojiconUtils.ee_10,
        EmojiconUtils.ee_11,
        EmojiconUtils.ee_12,
        EmojiconUtils.ee_13,
        EmojiconUtils.ee_14,
        EmojiconUtils.ee_15,
        EmojiconUtils.ee_16,
        EmojiconUtils.ee_17,
        EmojiconUtils.ee_18,
        EmojiconUtils.ee_19,
        EmojiconUtils.ee_20,
        EmojiconUtils.ee_21,
        EmojiconUtils.ee_22,
        EmojiconUtils.ee_23,
        EmojiconUtils.ee_24,
        EmojiconUtils.ee_25,
        EmojiconUtils.ee_26,
        EmojiconUtils.ee_27,
        EmojiconUtils.ee_28,
        EmojiconUtils.ee_29,
        EmojiconUtils.ee_30,
        EmojiconUtils.ee_31,
        EmojiconUtils.ee_32,
        EmojiconUtils.ee_33,
        EmojiconUtils.ee_34,
        EmojiconUtils.ee_35,
       
    };
    
    private static int[] icons = new int[]{
        R.drawable.ee_1,  
        R.drawable.ee_2,
        R.drawable.ee_3,  
        R.drawable.ee_4,  
        R.drawable.ee_5,  
        R.drawable.ee_6,  
        R.drawable.ee_7,  
        R.drawable.ee_8,  
        R.drawable.ee_9,  
        R.drawable.ee_10,  
        R.drawable.ee_11,  
        R.drawable.ee_12,  
        R.drawable.ee_13,  
        R.drawable.ee_14,  
        R.drawable.ee_15,  
        R.drawable.ee_16,  
        R.drawable.ee_17,  
        R.drawable.ee_18,  
        R.drawable.ee_19,  
        R.drawable.ee_20,  
        R.drawable.ee_21,  
        R.drawable.ee_22,  
        R.drawable.ee_23,  
        R.drawable.ee_24,  
        R.drawable.ee_25,  
        R.drawable.ee_26,  
        R.drawable.ee_27,  
        R.drawable.ee_28,  
        R.drawable.ee_29,  
        R.drawable.ee_30,  
        R.drawable.ee_31,  
        R.drawable.ee_32,  
        R.drawable.ee_33,  
        R.drawable.ee_34,  
        R.drawable.ee_35,  
    };
    
    
    private static final Emojicon[] DATA = createData();
    
    private static Emojicon[] createData(){
        Emojicon[] datas = new Emojicon[icons.length];
        for(int i = 0; i < icons.length; i++){
            datas[i] = new Emojicon(icons[i], emojis[i], Type.NORMAL);
        }
        return datas;
    }
    
    public static Emojicon[] getData(){
        return DATA;
    }
}
