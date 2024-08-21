# カリキュラム管理アプリ

## 概要
学習塾において生徒ごとにカリキュラムを作成し、学習を管理するためのWebアプリケーションです。<br>
先生ユーザーは、塾で扱うテキストをベースに各生徒のカリキュラムの作成/編集、学習の管理を行うことができます。<br>
生徒ユーザーは、自身のカリキュラムを閲覧、学習の管理を行うことができます。<br>
<br>
<br>


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




