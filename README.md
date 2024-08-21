# カリキュラム管理アプリ

## 概要
学習塾において生徒ごとにカリキュラムを作成し、学習を管理するためのWebアプリケーションです。<br>
先生ユーザーは、塾で扱うテキストをベースに各生徒のカリキュラムの作成/編集、学習の管理を行うことができます。<br>
生徒ユーザーは、自身のカリキュラムを閲覧、学習の管理を行うことができます。<br>
<br><br>



## 機能一覧

### ユーザー関連機能
| ログイン/ログアウト | パスワード変更機能 |
|---------------|----------------|
| ![loginout](https://github.com/user-attachments/assets/4385d7a8-1180-48db-a881-da0ead3e7c58) | ![newPass](https://github.com/user-attachments/assets/33453fad-f374-4852-a921-d2b128ba586c) |
| メールアドレスとパスワードでログイン、ログアウトができます。 | ログイン時にパスワードを変更することができます。 |

| ユーザー登録機能 | コース登録機能 |
|---------------|----------------|
| ![addUser](https://github.com/user-attachments/assets/836051e9-e116-4ed5-9999-501dafe61a88) | ![addCourse](https://github.com/user-attachments/assets/79f39412-91fb-4871-aeb5-11dff88d907e) |
| 管理者ユーザーであれば、他のユーザーを登録することができます。 | 先生/管理者ユーザーであれば、生徒ユーザーにコースを登録することができます。|

<br>

### カリキュラム作成/編集機能
先生/管理者ユーザーであれば、生徒ユーザーに登録したコースごとにカリキュラムを作成、編集できます。<br>
単元を自分で作成するか、テキストから選択して追加できます。
| 単元を作成して追加 | 単元をテキストから追加 |
|---------------|----------------|
| ![addCurriculum1](https://github.com/user-attachments/assets/520a7823-1522-4819-9820-d32eb43cea4e) | ![addCurriculum2](https://github.com/user-attachments/assets/82960c7f-2cbf-4013-8f59-38d9be0df435) |
| 単元を自分で作成してカリキュラムに追加できます。 | 単元をテキストから選択してカリキュラムに追加できます。 |

| 編集機能  | 並べ替え機能 |
|---------------|----------------|
| ![editCurriculum](https://github.com/user-attachments/assets/4afe5f1e-5e3d-4ffa-8fb7-d880a602e337) | ![sortable](https://github.com/user-attachments/assets/50c9807b-bef1-4a0d-884e-ede54408a7fb) |
| テキストボックスからカリキュラム内容を編集できます。 | ドラッグ＆ドロップによりカリキュラムの順番を並べ替えることができます。 |

| 削除機能  |
|---------------|
| ![delete](https://github.com/user-attachments/assets/de6c9db4-54a4-48bb-87cf-56b8c6bc9d62) |
| 単元を削除できます。 |

<br>

### 学習管理機能
カリキュラムの理解度チェックと小テスト結果を登録できます。
| 先生/管理者ユーザー側 | 生徒ユーザー側 |
|---------------|---------------|
| ![check1](https://github.com/user-attachments/assets/7a8e1d1a-573c-40fc-b8af-2e18eba59106) | ![check2](https://github.com/user-attachments/assets/526a5942-2249-4e63-bdab-b277b45d19d6) |
| 各生徒ユーザーに対して登録できます。 | 自身のカリキュラムに対して登録できます。 |

<br><br>



## 使用技術
- フロントエンド : HTML/CSS/JavaScript(SortableJS)
- バックエンド : Java
- フレームワーク : Spring boot
- データベース : MySQL
<br>
<br>


## データベース作成クエリ
```mysql
CREATE DATABASE curriculumdb;

USE curriculumdb;

CREATE TABLE property (
	id INT NOT NULL,
	name VARCHAR(128) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO property VALUES (1, '管理者');
INSERT INTO property VALUES (2, '先生');
INSERT INTO property VALUES (3, '中１');
INSERT INTO property VALUES (4, '中２');
INSERT INTO property VALUES (5, '中３');
INSERT INTO property VALUES (6, '高１');
INSERT INTO property VALUES (7, '高２');
INSERT INTO property VALUES (8, '高３');

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
	last_name VARCHAR(128) NOT NULL,
	first_name VARCHAR(128) NOT NULL,
	email VARCHAR(256) NOT NULL,
	password VARCHAR(128) NOT NULL,
	property INT,
	PRIMARY KEY (id),
	UNIQUE KEY (email),
	FOREIGN KEY (property) REFERENCES property (id)
);

CREATE TABLE subject (
	id INT NOT NULL,
	name VARCHAR(128),
	PRIMARY KEY (id)
);

INSERT INTO subject VALUES(1, '数学');
INSERT INTO subject VALUES(2, '英語');

CREATE TABLE course (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(128),
	subject INT,
	user INT,
	start_month VARCHAR(10) NOT NULL,
	editor VARCHAR(128),
	edit_date DATE,
	PRIMARY KEY (id),
	FOREIGN KEY (user) REFERENCES user (id),
	FOREIGN KEY (subject) REFERENCES subject (id)
);

CREATE TABLE status (
	id INT NOT NULL,
	name VARCHAR(128) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO status VALUES(1, '未');
INSERT INTO status VALUES(2, '済');
INSERT INTO status VALUES(3, '要復習');

CREATE TABLE curriculum (
	id INT NOT NULL AUTO_INCREMENT,
	course INT,
	order_num INT NOT NULL,
	name VARCHAR(128),
	textbook INT,
	textbook_name VARCHAR(128),
	page INT,
	status INT DEFAULT 1,
	score INT,
	PRIMARY KEY (id),
	FOREIGN KEY (course) REFERENCES course (id),
	FOREIGN KEY (status) REFERENCES status (id)
);

CREATE TABLE textbook (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(128),
	subject INT,
	PRIMARY KEY (id),
	FOREIGN KEY (subject) REFERENCES subject (id)
);

INSERT INTO textbook VALUES(1, '数1基礎', 1);
INSERT INTO textbook VALUES(2, '数A基礎', 1);

CREATE TABLE unit (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(128),
	textbook INT,
	page INT,
	PRIMARY KEY (id),
	FOREIGN KEY (textbook) REFERENCES textbook (id)
);

INSERT INTO unit VALUES(1, '1.単項式と多項式(1)', 1, 2);
INSERT INTO unit VALUES(2, '2.単項式と多項式(2)', 1, 8);
INSERT INTO unit VALUES(3, '3.多項式の計算', 1, 14);
INSERT INTO unit VALUES(4, '4.分配法則と展開', 1, 20);
```
<br><br>



## 今後の課題
- テキスト一覧ページ作成
- ユーザー情報、コース情報編集機能
