// Program to use inteface methods based on class name.

class Test
{
	public static void main(String[] args) {
		String ClassName = args[0];
		try {
			Greet sample = (Greet)Class.forName(ClassName).newInstance();
			sample.greet();
		}
		catch (Exception exception) {
			System.out.println(exception);
			exception.printStackTrace();
		}
	}
}

