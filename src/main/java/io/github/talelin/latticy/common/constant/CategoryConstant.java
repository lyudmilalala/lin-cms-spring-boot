package io.github.talelin.latticy.common.constant;

import java.util.LinkedList;
import java.util.List;

public class CategoryConstant {

    public static final String CATEGORY_QUANTUM_ENTANGLEMENT = "Quantum Entanglement";
    public static final String CATEGORY_MACHINE_LEARNING = "Machine Learning";
    public static final String CATEGORY_QUANTUM_MATERIALS = "Quantum Materials";
    public static final String CATEGORY_QUANTUM_CONTROL = "Quantum Control";
    public static final String CATEGORY_QUANTUM_DEVICES = "Quantum Devices";
    public static final String CATEGORY_QUANTUM_SOFTWARE = "Quantum Software";

    public static final List<String> CATEGORY_LIST = new LinkedList<String>() {{
        add(CATEGORY_QUANTUM_ENTANGLEMENT);
        add(CATEGORY_MACHINE_LEARNING);
        add(CATEGORY_QUANTUM_MATERIALS);
        add(CATEGORY_QUANTUM_CONTROL);
        add(CATEGORY_QUANTUM_DEVICES);
        add(CATEGORY_QUANTUM_SOFTWARE);
    }};
}
