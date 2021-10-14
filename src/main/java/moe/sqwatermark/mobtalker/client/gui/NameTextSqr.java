package moe.sqwatermark.mobtalker.client.gui;


public class NameTextSqr {

		private EnumNamePostion postion = EnumNamePostion.LEFT;
		private String charaName = null;
		private int textColor = COLOR_DEFAULT;
		public static final int COLOR_DEFAULT = 0xffffff;
		public static final int COLOR_MOB = 0x79CDCD;
		public static final int COLOR_PLAYER = 0x7B68EE;

		/**
		 * @return the postion
		 */
		public EnumNamePostion getPostion() {
			return postion;
		}
		/**
		 * @param postion the postion to set
		 */
		public NameTextSqr setPostion(EnumNamePostion postion) {
			this.postion = postion;
			return this;
		}
		/**
		 * @return the charaName
		 */
		public String getCharaName() {
			return charaName;
		}

		/**
		 * @param charaName the charaName to set
		 */
		public NameTextSqr setCharaName(String charaName) {
			this.charaName = charaName;
			return this;
		}
		public void Clear(){
			this.charaName=null;
			this.postion=EnumNamePostion.LEFT;
			textColor=this.COLOR_DEFAULT;
		}
		/**
		 * @return the textColor
		 */
		public int getTextColor() {
			return textColor;
		}
		/**
		 * @param textColor the textColor to set
		 */
		public NameTextSqr setTextColor(int textColor) {
			this.textColor = textColor;
			return this;
		}
}
