package com.ayoview.anim.tween;

import java.util.ArrayList;
import java.util.List;

public class AnimateOneStep {
	
	//AnimationSet animationSet = new AnimationSet(false);
	List<AnimInfo> animInfos = new ArrayList<AnimInfo>();
	
//	public void add(Animation a){
//		//animationSet.addAnimation(a);
//		//animInfos.add(a.getClass().getSimpleName());
//	}
	
	public void add(AnimInfo a){
		
		animInfos.add(a);
		
//		if(a.name.equals(AnimInfo.TRANSLATE)){
//			TranslateAnimation an = new TranslateAnimation(0, 1, 0, 1);
//			add(an);
//		}
//		if(a.name.equals(AnimInfo.ALPHA)){
//			TranslateAnimation an = new TranslateAnimation(0, 1, 0, 1);
//			add(an);
//		}
//		if(a.name.equals(AnimInfo.ROTATE)){
//			TranslateAnimation an = new TranslateAnimation(0, 1, 0, 1);
//			add(an);
//		}
//		if(a.name.equals(AnimInfo.SCALE)){
//			TranslateAnimation an = new TranslateAnimation(0, 1, 0, 1);
//			add(an);
//		}
	}
	
	public int size(){
		return animInfos.size();
	}
	
	public AnimInfo getInfo(int index){
		return animInfos.get(index);
	}
}
