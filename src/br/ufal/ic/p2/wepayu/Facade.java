package br.ufal.ic.p2.wepayu;
import br.ufal.ic.p2.wepayu.Exception.DataFinalInvalida;
import br.ufal.ic.p2.wepayu.Exception.DataInicialNaoPodeSerDepois;
import br.ufal.ic.p2.wepayu.Exception.DataInicioInvalida;
import br.ufal.ic.p2.wepayu.Exception.DataInvalida;
import br.ufal.ic.p2.wepayu.Exception.EmpregadoNaoEhHorista;
import br.ufal.ic.p2.wepayu.Exception.EmpregadoNexiste;
import br.ufal.ic.p2.wepayu.Exception.HorasDevemSerNumericas;
import br.ufal.ic.p2.wepayu.Exception.HorasDevemSerPositivas;
import br.ufal.ic.p2.wepayu.Exception.IDempregadoNaoPodeSerNula;
import br.ufal.ic.p2.wepayu.models.Assalariado;
import br.ufal.ic.p2.wepayu.models.Comissionado;
import br.ufal.ic.p2.wepayu.models.Empregado;
import br.ufal.ic.p2.wepayu.models.Horista;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;



public class Facade {
    private Map<String, Empregado> empregados = new LinkedHashMap<>();



    // pro teste 1_1
    public Facade() {
        carregarSistema();
    }
   
    private void carregarSistema() {
        try {
            XMLDecoder decoder = new XMLDecoder(new FileInputStream("wepayu.xml"));
            this.empregados = (Map<String, Empregado>) decoder.readObject();
            decoder.close();
        } catch (Exception e) {
            this.empregados = new LinkedHashMap<>();
        }
    }



    public void zerarSistema() {
        this.empregados.clear();
    }



    private String idGerar() {
        Random random = new Random();
        String id;
        do {
            id = String.valueOf(random.nextInt(1000) + random.nextInt(35) + random.nextInt(849));
        } while (empregados.containsKey(id));
        return id;
    }



    public String criarEmpregado(String nome, String endereco, String tipo, String salario) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new Exception("Nome nao pode ser nulo.");
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new Exception("Endereco nao pode ser nulo.");
        }
        if (salario == null || salario.isEmpty()) {
            throw new Exception("Salario nao pode ser nulo.");
        }
        double salarioNumerico;
        try {
            salarioNumerico = Double.parseDouble(salario.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new Exception("Salario deve ser numerico.");
        }
        if (salarioNumerico < 0) {
            throw new Exception("Salario deve ser nao-negativo.");
        }
        if (tipo.equals("comissionado")) {
            throw new Exception("Tipo nao aplicavel.");
        }
        if (!tipo.equals("assalariado") && !tipo.equals("horista")) {
            throw new Exception("Tipo invalido.");
        }
       
        String id = idGerar();
        Empregado novoEmpregado;
        if (tipo.equals("assalariado")) {
            novoEmpregado = new Assalariado(nome, endereco, id, "cheque", salarioNumerico);
        } else {
            novoEmpregado = new Horista(nome, endereco, id, "cheque", salarioNumerico);
        }
        empregados.put(id, novoEmpregado);
        return id;
    }



    public void removerEmpregado(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new IDempregadoNaoPodeSerNula();
        }
        if (!empregados.containsKey(id)) {
            throw new EmpregadoNexiste();
        }
        empregados.remove(id);
    }



    public String criarEmpregado(String nome, String endereco, String tipo, String salario, String comissao) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new Exception("Nome nao pode ser nulo.");
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new Exception("Endereco nao pode ser nulo.");
        }
        if (salario == null || salario.isEmpty()) {
            throw new Exception("Salario nao pode ser nulo.");
        }
        if (comissao == null || comissao.isEmpty()) {
            throw new Exception("Comissao nao pode ser nula.");
        }
       
        double salarioNumerico, comissaoNumerica;
        try {
            salarioNumerico = Double.parseDouble(salario.replace(",", "."));
            comissaoNumerica = Double.parseDouble(comissao.replace(",", "."));
        } catch (NumberFormatException e) {
            if (e.getMessage().contains("salario"))
                throw new Exception("Salario deve ser numerico.");
            else
                throw new Exception("Comissao deve ser numerica.");
        }



        if (salarioNumerico < 0) {
            throw new Exception("Salario deve ser nao-negativo.");
        }
        if (comissaoNumerica < 0) {
            throw new Exception("Comissao deve ser nao-negativa.");
        }
       
        if (!tipo.equals("comissionado")) {
            throw new Exception("Tipo nao aplicavel.");
        }



        String id = idGerar();
        Empregado novoEmpregado = new Comissionado(nome, endereco, id, "cheque", salarioNumerico, comissaoNumerica);
        empregados.put(id, novoEmpregado);
        return id;
    }



    public String getAtributoEmpregado(String id, String atributo) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new IDempregadoNaoPodeSerNula();
        }
        Empregado empregado = empregados.get(id);
        if (empregado == null) {
            throw new EmpregadoNexiste();
        }
        switch (atributo) {
            case "nome":
                return empregado.getNome();
            case "endereco":
                return empregado.getEndereco();
            case "tipo":
                if (empregado instanceof Comissionado) return "comissionado";
                if (empregado instanceof Assalariado) return "assalariado";
                if (empregado instanceof Horista) return "horista";
                throw new Exception("Tipo de empregado desconhecido.");
            case "salario":
                if (empregado instanceof Comissionado) {
                    return String.format("%.2f", ((Comissionado) empregado).getSalario()).replace('.', ',');
                }
                if (empregado instanceof Assalariado) {
                    return String.format("%.2f", ((Assalariado) empregado).getSalario()).replace('.', ',');
                } else if (empregado instanceof Horista) {
                    return String.format("%.2f", ((Horista) empregado).getSalarioHora()).replace('.', ',');
                } else {
                    throw new Exception("Empregado não tem salário base.");
                }
            case "comissao":
                if (empregado instanceof Comissionado) {
                    return String.format("%.2f", ((Comissionado) empregado).getComissao()).replace('.', ',');
                }
                throw new Exception("Empregado não eh comissionado.");
            case "sindicalizado":
                return "false";
            default:
                throw new Exception("Atributo nao existe.");
        }
    }



    public String getEmpregadoPorNome(String nome, int indice) throws Exception {
        int contador = 0;
        for (Empregado emp : empregados.values()) {
            if (emp.getNome().equals(nome)) {
                contador++;
                if (contador == indice) {
                    return emp.getId();
                }
            }
        }
        throw new Exception("Nao ha empregado com esse nome.");
    }






   
    // teste 3 
    public void lancaCartao(String emp, String data, String horas) throws Exception {
        if (emp == null || emp.isEmpty()) {
            throw new IDempregadoNaoPodeSerNula();
        }
        Empregado empregado = empregados.get(emp);
        if (empregado == null) {
            throw new EmpregadoNexiste();
        }
        if (!(empregado instanceof Horista)) {
            throw new EmpregadoNaoEhHorista();
        }



        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(data);
        } catch (Exception e) {
            throw new DataInvalida();
        }



        double horasNumericas;
        try {
            horasNumericas = Double.parseDouble(horas.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new HorasDevemSerNumericas();
        }
        if (horasNumericas <= 0) {
            throw new HorasDevemSerPositivas();
        }
       
        ((Horista) empregado).lancaCartao(data, horasNumericas);
    }



    public String getHorasNormaisTrabalhadas(String emp, String dataInicial, String dataFinal) throws Exception {
        return getHorasTrabalhadas(emp, dataInicial, dataFinal, false);
    }



    public String getHorasExtrasTrabalhadas(String emp, String dataInicial, String dataFinal) throws Exception {
        return getHorasTrabalhadas(emp, dataInicial, dataFinal, true);
    }



    private String getHorasTrabalhadas(String emp, String dataInicial, String dataFinal, boolean extra) throws Exception {
        if (emp == null || emp.isEmpty()) {
            throw new IDempregadoNaoPodeSerNula();
        }
        Empregado empregado = empregados.get(emp);
        if (empregado == null) {
            throw new EmpregadoNexiste();
        }
        if (!(empregado instanceof Horista)) {
            throw new EmpregadoNaoEhHorista();
        }



     
    SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        sdf.setLenient(false);
        Date dataI;
        Date dataF;
        try {
            dataI = sdf.parse(dataInicial);
        } catch (Exception e) {
            throw new DataInicioInvalida();
        }
        try {
            dataF = sdf.parse(dataFinal);
        } catch (Exception e) {
            throw new DataFinalInvalida();
        }
        if (dataI.after(dataF))
            throw new DataInicialNaoPodeSerDepois();
   
    double totalHoras = 0.0;
    Horista horista = (Horista) empregado;
   
    for (Map.Entry<String, Double> entry : horista.getCartoesDePonto().entrySet()) {
        try {
            Date dataAtual = sdf.parse(entry.getKey());
            if (!dataAtual.before(dataI) && dataAtual.before(dataF)) {
                double horasDiarias = entry.getValue();
                if (extra) {
                    totalHoras += Math.max(0, horasDiarias - 8);
                } else {
                    totalHoras += Math.min(8, horasDiarias);
                }
            }
        } catch (Exception e) {
        }
    }
   
    if (totalHoras % 1 == 0) {
        return String.valueOf((int) totalHoras);
    } else {
        String formattedValue = String.format("%.2f", totalHoras).replace('.', ',');
        if (formattedValue.endsWith("0")) {
            return formattedValue.substring(0, formattedValue.length() - 1);
        }
        return formattedValue;
        }
    }
    // --- Fim teste 3 ---









//agora pra user story 4:
    public void lancaVenda(String emp, String data, String valor) throws Exception {
        if (emp == null || emp.isEmpty()) {
            throw new IDempregadoNaoPodeSerNula();
        }
        Empregado empregado = empregados.get(emp);
        if (empregado == null) {
            throw new EmpregadoNexiste();
        }
        if (!(empregado instanceof Comissionado)) {
            throw new Exception("Empregado nao eh comissionado.");
        }



        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(data);
        } catch (Exception e) {
            throw new DataInvalida();
        }



    double valorNumerico;
        try {
            valorNumerico = Double.parseDouble(valor.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new Exception("Valor deve ser numerico.");
        }
        if (valorNumerico <= 0) {
            throw new Exception("Valor deve ser positivo.");
        }
   
        ((Comissionado) empregado).lancaVenda(data, valorNumerico);
    }



    public String getVendasRealizadas(String emp, String dataInicial, String dataFinal) throws Exception {
        if (emp == null || emp.isEmpty()) {
            throw new IDempregadoNaoPodeSerNula();
        }
        Empregado empregado = empregados.get(emp);
        if (empregado == null) {
            throw new EmpregadoNexiste();
        }
        if (!(empregado instanceof Comissionado)) {
            throw new Exception("Empregado nao eh comissionado.");
        }



        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        sdf.setLenient(false);
        Date dataI;
        Date dataF;
        try {
            dataI = sdf.parse(dataInicial);
        } catch (Exception e) {
            throw new DataInicioInvalida();
        }
        try {
            dataF = sdf.parse(dataFinal);
        } catch (Exception e) {
            throw new DataFinalInvalida();
        }
        if (dataI.after(dataF)) {
            throw new DataInicialNaoPodeSerDepois();
        }



        double totalVendas = 0.0;
        Comissionado comissionado = (Comissionado) empregado;
            for (Map.Entry<String, Double> entry : comissionado.getVendas().entrySet()) {
                try {
                    Date dataAtual = sdf.parse(entry.getKey());
                    if (!dataAtual.before(dataI) && dataAtual.before(dataF)) {
                        totalVendas += entry.getValue();
                    }
    } catch (Exception e) {
            }
        }



    return String.format("%.2f", totalVendas).replace('.', ',');
    }



//fim da user story 4



//agora user story 5:
public void alteraEmpregado(String emp, String atributo, String valor) throws Exception {

    if (atributo.equals("sindicalizado") && valor.equals("false")) {
        Empregado empregado = empregados.get(emp);
        if (empregado == null) throw new Exception("Empregado nao existe.");
        empregado.setSindicalizado(false);
        empregado.setIdSindicato(null);
        empregado.setTaxaSindical(0.0);
        return;
    }
    throw new Exception("Operação não suportada.");
}



public void alteraEmpregado(String emp, String atributo, String valor, String idSindicato, String taxaSindical) throws Exception {
    
    if (atributo.equals("sindicalizado") && valor.equals("true")) {
        for (Empregado e : empregados.values()) {
            if (e.getIdSindicato() != null && e.getIdSindicato().equals(idSindicato)) {
                throw new Exception("Ha outro empregado com esta identificacao de sindicato");
            }
        }
        Empregado empregado = empregados.get(emp);
        if (empregado == null) throw new Exception("Empregado nao existe.");
        empregado.setSindicalizado(true);
        empregado.setIdSindicato(idSindicato);
        empregado.setTaxaSindical(Double.parseDouble(taxaSindical.replace(",", ".")));
        return;
    }
    throw new Exception("Operação não suportada.");
}



//user story 5-taxa de servico
public void lancaTaxaServico(String membro, String data, String valor) throws Exception {
    if (membro == null || membro.isEmpty()) throw new Exception("Identificacao do membro nao pode ser nula.");
    Empregado empregado = null;
    for (Empregado e : empregados.values()) {
        if (e.getIdSindicato() != null && e.getIdSindicato().equals(membro)) {
            empregado = e;
            break;
        }
    }
    if (empregado == null) throw new Exception("Membro nao existe.");



    SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
    sdf.setLenient(false);
    try { sdf.parse(data); }
    catch (Exception e) { throw new Exception("Data invalida."); }



    double valorDouble;
    try { valorDouble = Double.parseDouble(valor.replace(",", ".")); }
    catch (Exception e) { throw new Exception("Valor deve ser numerico."); }
    if (valorDouble <= 0) throw new Exception("Valor deve ser positivo.");



    empregado.lancaTaxaServico(data, valorDouble);
}



public String getTaxasServico(String emp, String dataInicial, String dataFinal) throws Exception {
    Empregado empregado = empregados.get(emp);
    if (empregado == null) throw new Exception("Empregado nao existe.");
    if (!empregado.isSindicalizado()) throw new Exception("Empregado nao eh sindicalizado.");



    SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
    sdf.setLenient(false);
    Date dataI, dataF;
    try { dataI = sdf.parse(dataInicial); }
    catch (Exception e) { throw new Exception("Data inicial invalida."); }
    try { dataF = sdf.parse(dataFinal); }
    catch (Exception e) { throw new Exception("Data final invalida."); }
    if (dataI.after(dataF)) throw new Exception("Data inicial nao pode ser posterior aa data final.");



    double total = 0.0;
    Map<String, Double> taxas = empregado.getTaxasServico();
    for (Map.Entry<String, Double> entry : taxas.entrySet()) {
        Date dataTaxa = sdf.parse(entry.getKey());
        if (!dataTaxa.before(dataI) && dataTaxa.before(dataF)) {
            total += entry.getValue();
        }
    }
    return String.format("%.2f", total).replace('.', ',');
}






    public void encerrarSistema() {
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream("wepayu.xml"));
            encoder.writeObject(this.empregados);
            encoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

