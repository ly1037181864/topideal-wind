package snsoft.admin.util;

import java.security.MessageDigest;
import com.baomidou.kisso.common.encrypt.Byte2Hex;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import snsoft.admin.constant.SnBaseConstant;
/**
 * <p>��Ŀ���⣺ </p>
 * <p>��Ŀ���ܣ� </p>
 * <p>����ģ�飺 </p>
 * <p>����ƽ̨��Window10</p>
 * <p>�������ߣ�Eclipse</p>
 * <p>jar��:TODO</p>
 * <p>�������ڣ�2018��5��23�� ����3:42:11</p>
 * <p>��Ŀ���ߣ�����</p>
 * <p>��ȫ����snsoft.admin.comm.SnSaltEncoderUtil</p>
 * @version 1.0
 */
public class SnSaltEncoderUtil
{
	/**
	 * ��ֵ
	 */
	private String	salt;
	/**
	 * �㷨
	 */
	private String	algorithm;

	protected SnSaltEncoderUtil()
	{
		/* ���� */
	}

	public SnSaltEncoderUtil(String salt, String algorithm)
	{
		this.salt = salt;
		this.algorithm = algorithm;
	}

	/**
	 * 
	 * <p>
	 * md5 ��ֵ�����ַ���
	 * </p>
	 * 
	 * @param salt
	 * 				��ֵ
	 * @param rawText
	 *				��Ҫ���ܵ��ַ���
	 * @return
	 */
	public static String md5SaltEncode(String salt, String rawText)
	{
		return new SaltEncoder(salt, SnBaseConstant.ALGORITHM).encode(rawText);
	}

	/**
	 * 
	 * <p>
	 * �ж�md5 ��ֵ���������Ƿ���ȷ
	 * </p>
	 * 
	 * @param salt
	 * 				��ֵ
	 * @param encodeText
	 * 				���ܺ���ı�����
	 * @param rawText
	 * 				����ǰ���ı�����
	 * @return
	 */
	public static boolean md5SaltValid(String salt, String encodeText, String rawText)
	{
		return new SaltEncoder(salt, SnBaseConstant.ALGORITHM).isValid(encodeText, rawText);
	}

	/**
	 * 
	 * <p>
	 * �ַ�����ֵ����
	 * </p>
	 * 
	 * @param rawText
	 *            ��Ҫ���ܵ��ַ���
	 * @return
	 */
	public String encode(String rawText)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance(algorithm);
			//���ܺ���ַ���  
			return Byte2Hex.byte2Hex(md.digest(mergeRawTextAndSalt(rawText).getBytes(SnBaseConstant.ENCODING)));
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * <p>
	 * �жϼ��������Ƿ���ȷ
	 * </p>
	 * 
	 * @param encodeText
	 * 				���ܺ���ı�����
	 * @param rawText
	 * 				����ǰ���ı�����
	 * @return
	 */
	public boolean isValid(String encodeText, String rawText)
	{
		return this.encode(rawText).equals(encodeText);
	}

	/**
	 * 
	 * <p>
	 * �ϲ�������ֵ����������
	 * </p>
	 * 
	 * @param rawText
	 * 				��Ҫ���ܵ��ַ���
	 * @return
	 */
	private String mergeRawTextAndSalt(String rawText)
	{
		if (rawText == null)
		{
			rawText = "";
		}
		if (this.salt == null || "".equals(this.salt))
		{
			return rawText;
		} else
		{
			StringBuffer mt = new StringBuffer();
			mt.append(rawText);
			mt.append(SnBaseConstant.CUT_SYMBOL);
			mt.append(this.salt);
			return mt.toString();
		}
	}

	public String getSalt()
	{
		return salt;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	public String getAlgorithm()
	{
		return algorithm;
	}

	public void setAlgorithm(String algorithm)
	{
		this.algorithm = algorithm;
	}
}
