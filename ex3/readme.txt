src��,OberonScanner.java����JFlex�����Ĵʷ�������,�����²�������:
�ٿ��Խ�src\oberon��������lib/JFex.jarͬ��Ŀ¼��;
�ڽ����Ŀ¼,����"java -jar JFlex.jar oberon.flex"

gen.bat	����oberon.cup������������,����һ�����������Parser.java
build.bat	�������е�java����
doc.bat	Ϊjava��������javadoc�ĵ�
run.bat	����src\testcases\factorialGcd.*�Ĵ���������Ƿ���ȷ,��ȷ�Ļ����ɺ������ù�ϵͼ
test.bat	����src\testcases\sample.*�Ĵ�����﷨�Ƿ���ȷ,��ȷ�Ļ����ɺ������ù�ϵͼ




ps:
This flex file "oberon.flex" in ex3 is different from the one in the ex2.This one is used with cup, that one is not.