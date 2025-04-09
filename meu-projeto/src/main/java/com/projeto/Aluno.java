package projeto;

import java.time.LocalDate;
import java.util.UUID;

public class Aluno {
    private String id;
    public String nome;
    public String matricula;
    public String curso;
    public LocalDate dataNascimento;
    public String cpf;
    public String email;
    public String telefone;

    public Aluno(String nome, String matricula, String curso, LocalDate dataNascimento, String cpf, String email, String telefone) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso(){
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void exibirInformacoes() {
        System.out.println("Aluno: ");
        System.out.println("Matricula: " + matricula);
        System.out.println("Nome: " + nome);
        System.out.println("Curso: " + curso);
        System.out.println("Data de nascimento: " + dataNascimento);
        System.out.println("email: " + email);
        System.out.println("Telefone: " + telefone);
    }
}
