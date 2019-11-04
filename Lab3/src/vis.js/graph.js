const nodeColor = '#98caf9';
const startColor = '#ff5530';
const acceptColor = '#7BE141';
const edgeColor = '#008fe6';
nodes_json = [{ id: 0, label: 's0', color: startColor },
{ id: 1, label: 's1', color: nodeColor },
{ id: 2, label: 's2', color: nodeColor },
{ id: 3, label: 's3', color: nodeColor },
{ id: 4, label: 's4', color: nodeColor },
{ id: 5, label: 's5', color: acceptColor },
{ id: 6, label: 's6', color: acceptColor },
{ id: 7, label: 's7', color: acceptColor },
];
var nodes = new vis.DataSet(nodes_json);
edges_json = [
{ from: 0, to: 1, arrows: 'to', color: { color: edgeColor }, label: '0', font: { align: 'middle' }  },
{ from: 0, to: 2, arrows: 'to', color: { color: edgeColor }, label: '1', font: { align: 'middle' }  },
{ from: 1, to: 2, arrows: 'to', color: { color: edgeColor }, label: '1', font: { align: 'middle' }  },
{ from: 2, to: 1, arrows: 'to', color: { color: edgeColor }, label: '0', font: { align: 'middle' }  },
{ from: 1, to: 1, arrows: 'to', color: { color: edgeColor }, label: '0', font: { align: 'middle' }  },
{ from: 2, to: 3, arrows: 'to', color: { color: edgeColor }, label: '1', font: { align: 'middle' }  },
{ from: 3, to: 3, arrows: 'to', color: { color: edgeColor }, label: '1', font: { align: 'middle' }  },
{ from: 3, to: 4, arrows: 'to', color: { color: edgeColor }, label: '0', font: { align: 'middle' }  },
{ from: 4, to: 2, arrows: 'to', color: { color: edgeColor }, label: '1', font: { align: 'middle' }  },
{ from: 4, to: 5, arrows: 'to', color: { color: edgeColor }, label: '0', font: { align: 'middle' }  },
{ from: 5, to: 6, arrows: 'to', color: { color: edgeColor }, label: '1', font: { align: 'middle' }  },
{ from: 6, to: 7, arrows: 'to', color: { color: edgeColor }, label: '1', font: { align: 'middle' }  },
{ from: 7, to: 7, arrows: 'to', color: { color: edgeColor }, label: '1', font: { align: 'middle' }  },
{ from: 7, to: 4, arrows: 'to', color: { color: edgeColor }, label: '0', font: { align: 'middle' }  },
{ from: 5, to: 1, arrows: 'to', color: { color: edgeColor }, label: '0', font: { align: 'middle' }  },
{ from: 6, to: 1, arrows: 'to', color: { color: edgeColor }, label: '0', font: { align: 'middle' }  },
];
var edges = new vis.DataSet(edges_json);
var container = document.getElementById('myNetwork');
var data = {
    nodes: nodes,
    edges: edges
};
var options = {};
var network = new vis.Network(container, data, options);