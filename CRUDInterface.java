// Program to create interface for MySQL methods.

interface iCRUD {
	public void AddItem();
	public void ShowAll();
	public void Update();
	public void Search();
	public void Sort();
	public void Remove();
	public void CloseConnection();
}
