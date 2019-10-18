public class yangfan
{
	public static void main(String[]args)
	{
		char dj='B';
		switch(dj)
		{
		case 'A':
		{	System.out.println("得分在90到100分之中");
			break;
		}
		case 'B':
		{	System.out.println("得分在70到90分之中");
			break;
		}

		case 'C':
		{	System.out.println("得分在60到69分之中");
			break;
		}
		default:
		System.out.println("得分在0到59分之中");
		}
	}
}