package chaturanga.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static final AudioClip click = Applet.newAudioClip(Sound.class.getResource("../../art/clickmenu.wav"));
    public static final AudioClip hover = Applet.newAudioClip(Sound.class.getResource("../../art/hover.wav"));
    public static final AudioClip main_game_back_sound = Applet.newAudioClip(Sound.class.getResource("../../art/main-game-back-sound.wav"));
    public static final AudioClip clickpawn = Applet.newAudioClip(Sound.class.getResource("../../art/clickpawn.wav"));
    public static final AudioClip move = Applet.newAudioClip(Sound.class.getResource("../../art/move.wav"));
    public static final AudioClip win = Applet.newAudioClip(Sound.class.getResource("../../art/win.wav"));
    public static final AudioClip menu_back_sound = Applet.newAudioClip(Sound.class.getResource("../../art/menu-back-sound.wav"));
}
