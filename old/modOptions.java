package net.minecraft.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import net.minecraft.client.Minecraft;

/**
 * ���{���t�dŪ��mobTalker���Ѥ��i�w�q�]�m
 * 
 * @author FlammaRilva
 * 
 */
public class modOptions {
	private static final String RESOURCES_MOB_TALKER_CG = "/resources/mobTalker_cg/"; // �w�]CG���|
	private static final String MESG_SPEED = "mesgSpeed"; // Property������ܳt�ת��w�]����
	private static final String CG_FILE_LOCATION = "cgFileLocation"; // Property����CG���|���w�]����
	public static int[] itemID={2012};
	public static String[] itemName={"ItemMobTalker"};
	private Properties mainProperty = null; // �D�nProperty
	private Properties storeBack = new Properties(); // �^�s��Property
	private final File cfgLocation = new File(Minecraft.getMinecraftDir(),
			"/config/mobTalker_Config.cfg"); // �]�m�ɩ⹳���O
	private final File DEFAULT_CG_LOCATION = new File(
			Minecraft.getMinecraftDir(), RESOURCES_MOB_TALKER_CG); // CG���|�w�]�⹳���O
	private File cgFileLocation = DEFAULT_CG_LOCATION; // CG���|�⹳���O
	private int mesgSpeed = 1; // �T����ܳt��

	/**
	 * �غc�l�C�غc�ɦP�ɪ�l��
	 */
	public modOptions() throws IOException {
		/*
		 * �Y�w�]CG���|���s�b�h�إߤ@��
		 */
		if (!DEFAULT_CG_LOCATION.exists())
			DEFAULT_CG_LOCATION.mkdir();
		/*
		 * �Y�w�]�]�m�ɤ��s�b�h�إߤ@�Ӫťժ�
		 */
		if (!cfgLocation.exists())
			cfgLocation.createNewFile();
		/*
		 * �]�m�DProperty
		 */
		mainProperty = new Properties();
		mainProperty.load(new FileInputStream(cfgLocation));
		/*
		 * ��l�ƨ�l�ƾ�
		 */
		initData();

	}

	/**
	 * ��l�ơC������n�g�L�o�Ө�Ƥ~��B�@
	 */
	private void initData() throws FileNotFoundException, IOException {
		/*
		 * ���|�Ȧs�C���w�]��
		 */
		String TmpLoc = RESOURCES_MOB_TALKER_CG;
		/*
		 * �Y�Dproperty���]�m�h���X
		 */
		if (this.mainProperty == null)
			return;
		/*
		 * �b�DProperty�M��۩w�q��CG���|
		 */
		if (mainProperty.containsKey(CG_FILE_LOCATION)) {
			TmpLoc = (String) mainProperty.get(CG_FILE_LOCATION);
			cgFileLocation = new File(Minecraft.getMinecraftDir(), TmpLoc);
			/*
			 * �۩w�q���|���s�b�ɫإߤ@��
			 */
			if (!cgFileLocation.exists())
				cgFileLocation.mkdir();
		}
		/*
		 * �b�DProperty�̴M��۩w�q���T���t��
		 */
		if (mainProperty.containsKey(MESG_SPEED))
			this.mesgSpeed = Integer.parseInt((String) mainProperty
					.get(MESG_SPEED));
		/*
		 * �b�DProperty�̦s���D��ID
		 */
			for (int i=0;i<itemID.length;i++){
				if (mainProperty.containsKey(itemName[i])){
					itemID[i]= Integer.parseInt((String) mainProperty
							.get(itemName[i]));
				}
			}
		/*
		 * �]�m�s�^��Property���e
		 */
		storeBack.setProperty(CG_FILE_LOCATION, TmpLoc);
		storeBack.setProperty(MESG_SPEED, String.valueOf(this.mesgSpeed));
		for (int i=0;i<itemID.length;i++){
			storeBack.setProperty(itemName[i], String.valueOf(itemID[i]));
		}
		/*
		 * �s�^�ƾ�
		 */
		storeBack.store(new FileOutputStream(cfgLocation),
				"Mod MobTalker Configs");
	}

	/**
	 * @return CG���|�⹳���O
	 */
	public File getCgFileLocation() {
		return cgFileLocation;
	}

	/**
	 * @return �T����ܳt��
	 */
	public int getMesgSpeed() {
		return mesgSpeed;
	}
}
