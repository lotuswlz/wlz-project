-- ���ݶ���
create table t_data_meta (
	meta_id number(8) not null,
	title varchar2(200) not null,
	meta_description varchar2(1000),
	source number(1) not null,
	refereced_str varchar2(1024),
	create_time date default sysdate not null,
	CONSTRAINT pk_data_meta
		PRIMARY KEY (meta_id) USING INDEX TABLESPACE tp_report_idx
) tablespace tp_report;
-- ������
create table t_report_template (
	template_id number(8) not null,
	title varchar2(400) not null,
	description varchar2(1000),
	create_time date default sysdate not null,
	is_avaliable number(1) default 1 not null,
	CONSTRAINT pk_report_template
		PRIMARY KEY (template_id) USING INDEX TABLESPACE tp_report_idx
) tablespace tp_report;
-- ����ģ���Ӧ������
create table t_report_template_column (
	template_id number(8) not null,
	column_id number(8) not null,
	sort number(5) not null,
	CONSTRAINT fk_rpt_data_meta_id
		FOREIGN KEY (column_id) REFERENCES t_data_meta(meta_id),
	CONSTRAINT fk_rpt_column_template_id
		FOREIGN KEY (template_id) REFERENCES t_report_template(template_id)
) tablespace tp_report;
-- Ȩ������
create table t_privilege_description (
	privilege_id number(5) not null,
	title varchar2(400) not null,
	description varchar2(1000),
	CONSTRAINT pk_privilege_description
		PRIMARY KEY (privilege_id) USING INDEX TABLESPACE tp_report_idx
) tablespace tp_report;
-- Ȩ�޺����ݶ���Ĺ���������ѡ�������������Ҳ���ڲ鿴����ʱ��֤Ȩ��
create table t_data_privilege_relation (
	privilege_id number(10) not null,
	data_meta_id number(8) not null,
	CONSTRAINT fk_relation_data_privilege
		FOREIGN KEY (privilege_id) REFERENCES t_privilege_description(privilege_id),
	CONSTRAINT fk_relation_data_meta
		FOREIGN KEY (data_meta_id) REFERENCES t_data_meta(meta_id)
) tablespace tp_report;
-- ���ɵı���
create table t_report (
	/* 8 of date, append with 3 sequence*/
	report_id number(11) not null,
	template_id number(8) not null,
	fiil_date date default sysdate not null,
	range_type number(2) default 0 not null,/*��������0:week report; 1:month report*/
	CONSTRAINT pk_report
		PRIMARY KEY (report_id) USING INDEX TABLESPACE tp_report_idx,
	CONSTRAINT fk_report_template_id
		FOREIGN KEY (template_id) REFERENCES t_report_template(template_id)
) tablespace tp_report;
-- ���ɱ��������
create table t_report_data (
	data_id number(8) not null,
	report_id number(11) not null,
	column_id number(8) not null,
	begin_date date not null,
	end_date date not null,
	fiil_date date default sysdate not null,
	value number(12,2),/*can be null, maybe some data is N/A*/
	CONSTRAINT pk_report_data
		PRIMARY KEY (data_id) USING INDEX TABLESPACE tp_report_idx,
	CONSTRAINT fk_rpt_data_meta_id
		FOREIGN KEY (column_id) REFERENCES t_data_meta(meta_id)
) tablespace tp_report;

/**
	1������Ȩ���飺
		1������ԱȨ��
		2��CEO
		3��������leader
		4��������Ա��
		5��Public
	2��Ϊÿ������ָ��Ȩ���飬��Զ��ϵ��ÿ������������Ȩ�޿ɷ��ʣ��ݲ��ṩȨ��������
	3����������ģ��
		1�������Ȩ�޴�����һ��������������ݱ���������������ڣ�ͬһȨ�ޡ�
		2��ѡ�����������ָ��sort
	4��Ϊ����ģ����������
		1��ָ���������䣬�������ͣ��ܱ����±���
		2����������
			��1��ǿ���������ɹ���Ա�ɼ���ȫ�������еı�������̨���ɡ�
			��2��������Ҫ���ɵı��������ݳ��в�ѯ��Ҫ���ɵ������
	5������sort�����excel
*/