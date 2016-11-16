#include<iostream>
#include<string>
#include<fstream>
#include<sstream>
#include<windows.h>
#include<stdio.h>
using namespace std;

const int fps = 25;  //帧数

const string src_file = "bit img";
void print_img(string & filepath);

void print_img(string & filepath) {    //打印字符画，单位帧数时间后清屏
	string str_sum;
	string str_temp;
	ifstream in;
	in.open(filepath);
	while (getline(in, str_temp))
		str_sum += str_temp+'\n';
	cout << str_sum<<endl;
	Sleep(1000/fps);    //线程睡眠单位帧数时间
	system("cls");
}


int main() {
	const int count = 4200;  //总共要显示的帧数
	for (int i = 1; i <= count; i++) {
		stringstream stream;
		stream << i;
		string index = stream.str();
		string fileImg = src_file + "/" + index + ".txt";
		print_img(fileImg);		//打印图像
	}

	return 0;
	
}