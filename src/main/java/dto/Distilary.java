package dto;

public class Distilary {
	//フィールドの生成
	private final Integer  id;
	private final String name;
	private final String category;
	
	//コンストラクタ
	public Distilary(Integer id, String name, String category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}

	//getterメソッド
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
	}

}

/*DROP TABLE IF EXISTS TEST;
CREATE TABLE TEST(ID INT PRIMARY KEY,
   NAME VARCHAR(255));
INSERT INTO TEST VALUES(1, 'Hello');
INSERT INTO TEST VALUES(2, 'World');
SELECT * FROM TEST ORDER BY ID;
UPDATE TEST SET NAME='Hi' WHERE ID=1;
DELETE FROM TEST WHERE ID=2;*/