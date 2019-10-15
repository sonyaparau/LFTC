const nodeColor = '#98caf9';
const startColor = '#ff5530';
const acceptColor = '#7BE141';
const edgeColor = '#008fe6';
nodes_json = [{ id: 0, label: 's0', color: startColor },
{ id: 1, label: 's1', color: nodeColor },
{ id: 2, label: 's2', color: acceptColor },
{ id: 3, label: 's3', color: acceptColor },
{ id: 4, label: 's4', color: nodeColor },
{ id: 5, label: 's5', color: acceptColor },
];
var nodes = new vis.DataSet(nodes_json);
edges_json = [
{ from: 0, to: 1, arrows: 'to', color: { color: edgeColor }, label: 'z', font: { align: 'middle' }  },
{ from: 1, to: 1, arrows: 'to', color: { color: edgeColor }, label: 'e', font: { align: 'middle' }  },
{ from: 1, to: 3, arrows: 'to', color: { color: edgeColor }, label: 't', font: { align: 'middle' }  },
{ from: 0, to: 2, arrows: 'to', color: { color: edgeColor }, label: 'e', font: { align: 'middle' }  },
{ from: 0, to: 4, arrows: 'to', color: { color: edgeColor }, label: 's', font: { align: 'middle' }  },
{ from: 4, to: 5, arrows: 'to', color: { color: edgeColor }, label: 'i', font: { align: 'middle' }  },
];
var edges = new vis.DataSet(edges_json);
var container = document.getElementById('myNetwork');
var data = {
    nodes: nodes,
    edges: edges
};
var options = {};
var network = new vis.Network(container, data, options);