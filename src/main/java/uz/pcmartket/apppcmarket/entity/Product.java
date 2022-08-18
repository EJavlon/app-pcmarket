package uz.pcmartket.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pcmartket.apppcmarket.entity.template.Technique;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Category category;

    @OneToMany
    private List<Attachment> images;

    private Double price;

    private Date guarantee;

    @OneToMany
    private Set<Memory> memory = new HashSet<>();

    @OneToMany
    private Set<Monitor> monitor = new HashSet<>();

    @OneToMany
    private Set<Monobloc> monobloc = new HashSet<>();

    @OneToMany
    private Set<Motherboard> motherboard = new HashSet<>();

    @OneToMany
    private Set<Notebook> notebook = new HashSet<>();

    @OneToMany
    private Set<PC> pc = new HashSet<>();

    @OneToMany
    private Set<PowerUnit> powerUnit = new HashSet<>();

    @OneToMany
    private Set<Processor> processor = new HashSet<>();

    @OneToMany
    private Set<Ram> ram = new HashSet<>();

    @OneToMany
    private Set<VideoCart> videoCart = new HashSet<>();


    public void addMemoryProduct(Memory memory) {
        this.memory.add(memory);
    }

    public void addMotnitorProduct(Monitor monitor) {
        this.monitor.add(monitor);
    }

    public void addMonoblocProduct(Monobloc monobloc) {
        this.monobloc.add(monobloc);
    }

    public void addMotherboardProduct(Motherboard motherboard) {
        this.motherboard.add(motherboard);
    }

    public void addNotebookProduct(Notebook notebook) {
        this.notebook.add(notebook);
    }

    public void addPCProduct(PC pc) {
        this.pc.add(pc);
    }

    public void addPowerUnitProduct(PowerUnit powerUnit) {
        this.powerUnit.add(powerUnit);
    }

    public void addProcessorProduct(Processor processor) {
        this.processor.add(processor);
    }

    public void addRamProduct(Ram ram) {
        this.ram.add(ram);
    }

    public void addVideoCartProduct(VideoCart videoCart) {
        this.videoCart.add(videoCart);
    }
}
