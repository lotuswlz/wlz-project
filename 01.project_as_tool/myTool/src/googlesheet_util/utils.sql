-- 数据定义
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
-- 报表定义
create table t_report_template (
	template_id number(8) not null,
	title varchar2(400) not null,
	description varchar2(1000),
	create_time date default sysdate not null,
	is_avaliable number(1) default 1 not null,
	CONSTRAINT pk_report_template
		PRIMARY KEY (template_id) USING INDEX TABLESPACE tp_report_idx
) tablespace tp_report;
-- 报表模版对应数据项
create table t_report_template_column (
	template_id number(8) not null,
	column_id number(8) not null,
	sort number(5) not null,
	CONSTRAINT fk_rpt_data_meta_id
		FOREIGN KEY (column_id) REFERENCES t_data_meta(meta_id),
	CONSTRAINT fk_rpt_column_template_id
		FOREIGN KEY (template_id) REFERENCES t_report_template(template_id)
) tablespace tp_report;
-- 权限描述
create table t_privilege_description (
	privilege_id number(5) not null,
	title varchar2(400) not null,
	description varchar2(1000),
	CONSTRAINT pk_privilege_description
		PRIMARY KEY (privilege_id) USING INDEX TABLESPACE tp_report_idx
) tablespace tp_report;
-- 权限和数据定义的关联，用于选择数据项创建报表，也用于查看报表时验证权限
create table t_data_privilege_relation (
	privilege_id number(10) not null,
	data_meta_id number(8) not null,
	CONSTRAINT fk_relation_data_privilege
		FOREIGN KEY (privilege_id) REFERENCES t_privilege_description(privilege_id),
	CONSTRAINT fk_relation_data_meta
		FOREIGN KEY (data_meta_id) REFERENCES t_data_meta(meta_id)
) tablespace tp_report;
-- 生成的报表
create table t_report (
	/* 8 of date, append with 3 sequence*/
	report_id number(11) not null,
	template_id number(8) not null,
	fiil_date date default sysdate not null,
	range_type number(2) default 0 not null,/*表期类型0:week report; 1:month report*/
	CONSTRAINT pk_report
		PRIMARY KEY (report_id) USING INDEX TABLESPACE tp_report_idx,
	CONSTRAINT fk_report_template_id
		FOREIGN KEY (template_id) REFERENCES t_report_template(template_id)
) tablespace tp_report;
-- 生成报表的数据
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
	1，划分权限组：
		1）管理员权限
		2）CEO
		3）各部门leader
		4）各部门员工
		5）Public
	2，为每个数据指定权限组，多对多关系，每个数据允许多个权限可访问，暂不提供权限树功能
	3，创建报表模版
		1）需根据权限创建，一个报表的所有数据必须包含（但不限于）同一权限。
		2）选择并排列数据项，指定sort
	4，为报表模版生成数据
		1）指定日期区间，表期类型（周报，月报）
		2）生成数据
			（1）强制首先生成管理员可见的全部均含有的报表，即后台生成。
			（2）根据需要生成的报表，在数据池中查询需要生成的数据项。
	5，根据sort填充至excel
*/