
public class Animations {
	private int delay;
	Animations(int delay) {
		this.delay=delay;
	}
	
	
	
	public int Anim(int Anim_delay, int AnimCount, int animcount,int Animpos,int Dir) {
		animcount--;

		if (animcount <= 0) {

			animcount = Anim_delay;
			Animpos = Animpos + Dir;

			if (Animpos == (AnimCount - 1) || Animpos == 0) {
				Dir = -Dir;
			}
		}
		
		
		return Animpos;
	}
	
}
