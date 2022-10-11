package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ReportUtil implements Serializable{

	private static final long serialVersionUID = 1L;

	public byte[] gerarRelatorioPDF(List listaDados, String nomeRelatorio, ServletContext servletContext) throws Exception{
		
		JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(listaDados);
		String path = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap(), jrBean);		
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
}
