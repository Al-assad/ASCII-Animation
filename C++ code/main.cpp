#include<iostream>
#include<string>
#include<fstream>
#include<sstream>
#include<windows.h>
#include<stdio.h>
using namespace std;

const int fps = 25;  //֡��

const string src_file = "bit img";
void print_img(string & filepath);

void print_img(string & filepath) {    //��ӡ�ַ�������λ֡��ʱ�������
	string str_sum;
	string str_temp;
	ifstream in;
	in.open(filepath);
	while (getline(in, str_temp))
		str_sum += str_temp+'\n';
	cout << str_sum<<endl;
	Sleep(1000/fps);    //�߳�˯�ߵ�λ֡��ʱ��
	system("cls");
}


int main() {
	const int count = 4200;  //�ܹ�Ҫ��ʾ��֡��
	for (int i = 1; i <= count; i++) {
		stringstream stream;
		stream << i;
		string index = stream.str();
		string fileImg = src_file + "/" + index + ".txt";
		print_img(fileImg);		//��ӡͼ��
	}

	return 0;
	
}