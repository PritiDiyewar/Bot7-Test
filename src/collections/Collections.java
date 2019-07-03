/**
 * 
 */
package collections;

import java.util.ArrayList;

/**
 * @author PD
 *
 */
public class Collections {

	public Collections() {}
	
	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	private ArrayList<String> list = new ArrayList<String>();
		
	public void arrayMethod(String itemToInsert) {
		list.add(itemToInsert);
		System.out.println(list.size());
		System.out.println(list.get(0));
		
	}
}
