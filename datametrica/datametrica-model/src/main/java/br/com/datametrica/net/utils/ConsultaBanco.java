package br.com.datametrica.net.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.datametrica.net.model.DoneByAgent;

public class ConsultaBanco {
    static List<DoneByAgent> queryResultTest = new ArrayList<DoneByAgent>();
    static List<DoneByAgent> consolidadoPorAgente = new ArrayList<DoneByAgent>();

    public static void main(String[] args) {
        queryResultTest = BeanCreatorUtils.createResultadoList();
        for (DoneByAgent resultado : queryResultTest) {
            DoneByAgent consolida = new DoneByAgent();
            DoneByAgent porAgente = new DoneByAgent();
            if (consolidadoPorAgente.isEmpty()) {
                consolida.setSupervisorName(resultado.getSupervisorName());
                consolida.setAgentName(resultado.getAgentName());
                consolida.setAgentLastName(resultado.getAgentLastName());
            } else {
                for (DoneByAgent porAgenteTmp : consolidadoPorAgente) {
                    if (consolidadoPorAgente.isEmpty()) {
                        consolida.setSupervisorName(resultado.getSupervisorName());
                        consolida.setAgentName(resultado.getAgentName());
                        consolida.setAgentLastName(resultado.getAgentLastName());
                        continue;
                    }
                    if (porAgenteTmp.getAgentLastName().equals(resultado.getAgentLastName()) && porAgenteTmp.getAgentName().equals(resultado.getAgentName())) {
                        consolida = porAgente = porAgenteTmp;
                        continue;
                    }
                    consolida.setSupervisorName(resultado.getSupervisorName());
                    consolida.setAgentName(resultado.getAgentName());
                    consolida.setAgentLastName(resultado.getAgentLastName());
                }
            }
            consolidadoPorAgente.remove((Object)porAgente);
            consolida.somaTipificacao(resultado.getClassification());
            consolida.totalizaTipificacao();
            consolidadoPorAgente.add(consolida);
            Collections.sort(consolidadoPorAgente, new DoneByAgent().new Ordenator());
        }
        System.out.println("***Consolidado por agente***");
        for (DoneByAgent print : consolidadoPorAgente) {
            System.out.print(" | ");
            System.out.print("Supervisor = " + print.getSupervisorName());
            System.out.print(" | ");
            System.out.print("Agente = " + print.getAgentName());
            System.out.print(" | ");
            System.out.print("PV1 = " + print.getPv1());
            System.out.print(" | ");
            System.out.print("PV2 = " + print.getPv2());
            System.out.print(" | ");
            System.out.print("CANCELADO = " + print.getCanceled());
            System.out.print(" | ");
            System.out.print("TOTAL = " + print.getTotal());
            System.out.print(" | ");
            System.out.println();
        }
        System.out.println("******");
    }
}